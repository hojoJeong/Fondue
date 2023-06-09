package com.ssafy.fundyou.ui.ar_capture_confirm

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentArCaptureBinding
import com.ssafy.fundyou.ui.ar_gallery.ArGalleryViewModel
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.util.extension.getFormattedCurrentTime
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class ArCaptureFragment : BaseFragment<FragmentArCaptureBinding>(R.layout.fragment_ar_capture) {
    private lateinit var bitmap: Bitmap
    private val arCaptureFragmentArgs by navArgs<ArCaptureFragmentArgs>()
    private val arGalleryViewModel by activityViewModels<ArGalleryViewModel>()
    private val arCaptureViewModel by activityViewModels<ArCaptureViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        bitmap = getBitmapFromArgs()
        binding.apply {
            ivCapture.setImageBitmap(bitmap)
            btnSave.setOnClickListener {
                uploadFirebase()
            }
        }
    }

    private fun uploadFirebase() {
        FirebaseApp.initializeApp(requireContext())
        val storage = Firebase.storage

        // Create a storage reference from our app
        val storageRef = storage.reference

        // Create a reference to "mountains.jpg"
        val imgPath = getFormattedCurrentTime() + "_${arGalleryViewModel.fundingItemId}.jpg"
        val sofaRef = storageRef.child(imgPath)

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = sofaRef.putBytes(data)
        uploadTask
            .addOnFailureListener {
                it.printStackTrace()
            }.addOnSuccessListener {
                val url = getString(R.string.fire_storage_url_prefix) + imgPath + getString(R.string.fire_storage_url_suffix)
                arCaptureViewModel.saveArImage(arGalleryViewModel.fundingItemId, url)
            }.addOnProgressListener {
                binding.pgUpload.apply {
                    this.visibility = View.VISIBLE
                }
            }.addOnPausedListener {
                Log.d("suyong", "uploadFirebase: Paused")
            }
    }

    private fun getBitmapFromArgs(): Bitmap {
        return arCaptureFragmentArgs.bitmap
    }

    override fun initViewModels() {
        initArImageSaveRequestObserver()
    }
    private fun initArImageSaveRequestObserver(){
        arCaptureViewModel.arImageSaveRequestStatus.observe(viewLifecycleOwner){ response ->
            when(response){
                true -> {
                    Log.d("TAG", "initArImageSaveRequestObserver: true")
                    navigate(ArCaptureFragmentDirections.actionArCaptureFragmentToArGalleryFragment(0,0))

                }
                false -> {
                    Log.d("TAG", "initArImageSaveRequestObserver: false")
                }
            }
        }
    }
}