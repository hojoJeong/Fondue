package com.ssafy.fundyou.ui.ar

import android.app.Activity
import android.app.ActivityManager
import android.content.Context

import android.graphics.Bitmap
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar

import com.google.ar.core.Anchor
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.assets.RenderableSource

import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.google.firebase.FirebaseApp
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentArBinding
import com.ssafy.fundyou.ui.ar_capture_confirm.ArCaptureFragmentArgs
import com.ssafy.fundyou.ui.ar_gallery.ArGalleryViewModel
import com.ssafy.fundyou.ui.common.BaseFragment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ArFragment : BaseFragment<FragmentArBinding>(R.layout.fragment_ar) {

    private var arFragment: ArFragment? = null
    private lateinit var renderable: ModelRenderable
    private val arGalleryFragmentViewModel by activityViewModels<ArGalleryViewModel>()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        arFragment = childFragmentManager.findFragmentById(R.id.arFragment) as ArFragment
        binding.apply {
            btnCapture.setOnClickListener {
                getBitmapFromView(requireView().findViewById(R.id.arFragment)) {
                    convertBMPtoPNG(it)
                }
            }
            btnCapture.isEnabled = false
            btnCapture.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.grey)
        }
    }

    override fun initViewModels() {}

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initFirebase()
    }

    /** Firebase Storage에서 가져올 데이터 초기화 */
    private fun initFirebase() {
        FirebaseApp.initializeApp(requireContext())
        val storage = FirebaseStorage.getInstance()
        // 파이어베이스 저장소에서 파일명으로 가져옴
        val modelRef = storage.reference.child("${arGalleryFragmentViewModel.itemId}.glb")
        Log.d("TAG", "initFirebase: ${arGalleryFragmentViewModel.itemId}.glb")
        // prefix_{itemId}.glb라는 빈파일 생성
        val file = File.createTempFile("prefix_${arGalleryFragmentViewModel.itemId}", "glb")
        // StorageReference에서 file에 파일을 비동기식으로 다운로드.
        writeFileFromFirebase(modelRef, file)
    }

    /** Firebase Storage에서 파일을 다운로드*/
    private fun writeFileFromFirebase(modelRef: StorageReference, file: File) {
        modelRef.getFile(file)
            .addOnSuccessListener { // 다운로드 성공
                buildModel(file)
                Log.d("suyong", "writeFileFromFirebase: ")
            }.addOnFailureListener { // 다운로드 실패
                Log.d("suyong", "writeFileFromFirebase: fail")
                Toast.makeText(context, "모델을 불러오지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
    }

    /**매개변수로 주어진 file을 3D 모델로 빌드*/
    private fun buildModel(file: File) {

        val renderableSource = RenderableSource.builder()
            .setSource(requireContext(), Uri.parse(file.path), RenderableSource.SourceType.GLB)
            .setRecenterMode(RenderableSource.RecenterMode.ROOT).build()

        ModelRenderable.builder()
            .setSource(context, renderableSource)
            .setRegistryId(file.path).build()
            .thenAccept { modelRenderable ->
                with(Snackbar.make(requireView(), "화면을 터치해서 상품을 배치하세요!", Snackbar.LENGTH_SHORT)) {
                    view.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                    show()
                }
                renderable = modelRenderable
                with(binding.btnCapture){
                    backgroundTintList = null
                    isEnabled = true
                }
                // ARCore 평면을 탭하면 호출됩니다.
                arFragment!!.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane?, motionEvent: MotionEvent? ->
                    val anchor = hitResult.createAnchor()
                    addModelToScene(anchor, renderable)
                }
            }.exceptionally {
                val toast = Toast.makeText(
                    context, "Unable to load renderable: " + file.path, Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                null
            }
    }

    /** 특정 위치에 모델을 배치 */
    private fun addModelToScene(anchor: Anchor, modelRenderable: ModelRenderable) {

        // ARCore 앵커를 기반으로 월드 공간에 자동으로 배치되는 노드
        val node = AnchorNode(anchor)

        // TransformationSystem의 동작을 사용하여 선택하고 변환하고 회전하고 확장할 수 있는 노드
        val transformableNode = TransformableNode(arFragment!!.transformationSystem)
        transformableNode.apply {
            setParent(node)
            scaleController.maxScale = 1.0f
            scaleController.minScale = 0.8f
            renderable = modelRenderable
        }

        // 현재 AR Scene에 노드를 등록
        arFragment!!.arSceneView.scene.addChild(node)
        // 설치된 노드를 선택상태로 만듬
        transformableNode.select()
    }

    /** 디바이스의 Sceneform 지원 여부 확인 */
    fun checkIsSupportedDeviceOrFinish(activity: Activity): Boolean {
        val openGlVersionString =
            (activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
                .deviceConfigurationInfo
                .glEsVersion
        if (openGlVersionString.toDouble() < MIN_OPENGL_VERSION) {
            Log.e("suyong", "Sceneform requires OpenGL ES 3.0 later")
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                .show()
            activity.finish()
            return false
        }
        return true
    }

    /** view에 표시된 픽셀을 비트맵으로 복사 */
    @RequiresApi(Build.VERSION_CODES.O)
    fun getBitmapFromView(view: View, callback: (Bitmap?) -> Unit) {
        // 비트맵 생성
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

        // 뷰의 좌표를 계산하여 배열에 x, y가 반환
        val locationOfViewInWindow = IntArray(2)
        view.getLocationInWindow(locationOfViewInWindow)
        try {
            PixelCopy.request(
                arFragment!!.arSceneView,
                Rect(
                    locationOfViewInWindow[0],
                    locationOfViewInWindow[1],
                    locationOfViewInWindow[0] + view.width,
                    locationOfViewInWindow[1] + view.height
                ),
                bitmap,
                { copyResult -> // PixelCopy.OnPixelCopyFinishedListener
                    if (copyResult == PixelCopy.SUCCESS) {
                        callback.invoke(bitmap)
                        binding.btnCapture.visibility = View.VISIBLE
                    } else callback.invoke(null)
                }, Handler(Looper.getMainLooper())
            )
        } catch (e: java.lang.IllegalArgumentException) {
            callback.invoke(null)
        }
    }

    /** 비트맵 -> png파일로 저장 */
    private fun convertBMPtoPNG(bitmap: Bitmap?) {
        try {
            Log.d("suyong", "sharing picture")

            /* 캐시 디렉터리에서 images*/
            val cachePath = File(requireContext().cacheDir, "images")
            Log.d("suyong", cachePath.path)

            /* cache/images 디렉터리가 없으면 생성 */
            cachePath.mkdirs()
            val stream = FileOutputStream("$cachePath/image.png")

            /* bitmap -> png */
            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()
            navigate(ArFragmentDirections.actionArFragmentToArCaptureFragment(bitmap = bitmap!!))

        } catch (e: IOException) {
            Log.d("suyong", "faild to sharing")
            e.printStackTrace()
        }
    }

    companion object {
        private const val MIN_OPENGL_VERSION = 3.0
    }
}