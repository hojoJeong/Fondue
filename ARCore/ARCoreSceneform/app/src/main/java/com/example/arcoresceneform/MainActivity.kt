package com.example.arcoresceneform

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.sceneform.ux.ArFragment
import android.os.Bundle
import com.google.ar.core.HitResult
import android.view.MotionEvent
import com.google.ar.core.Anchor
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.TransformableNode

class MainActivity : AppCompatActivity() {
    private var arFragment: ArFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment?

        // ARCore 평면을 탭하면 호출됩니다.
        arFragment!!.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane?, motionEvent: MotionEvent? ->
            val anchor = hitResult.createAnchor()
            // ModelRenderable 객체의 빌더를 설정한다.
            ModelRenderable.builder() // 소스 데이터는 assets/1.sfb 파일을 사용한다.
                .setSource(this, Uri.parse("TocoToucan.sfb")) // 빌드!
                .build() // 빌드 성공하면 소스 데이터로 렌더링된 모델을 설치한다.
                .thenAccept { modelRenderable: ModelRenderable ->
                    addModelToScene(
                        anchor,
                        modelRenderable
                    )
                }
        }
    }

    private fun addModelToScene(anchor: Anchor, modelRenderable: ModelRenderable) {
        // ARCore 앵커를 기반으로 월드 공간에 자동으로 배치되는 노드
        val node = AnchorNode(anchor)
        // TransformationSystem의 동작을 사용하여 선택하고 변환하고 회전하고 확장할 수 있는 노드
        val transformableNode = TransformableNode(arFragment!!.transformationSystem)
        transformableNode.setParent(node)
        transformableNode.renderable = modelRenderable
        // 현재 AR Scene에 노드를 등록
        arFragment!!.arSceneView.scene.addChild(node)
        // 설치된 노드를 선택상태로 만듬
        transformableNode.select()
    }
}