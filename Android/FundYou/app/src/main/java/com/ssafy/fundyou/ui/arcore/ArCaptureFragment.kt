package com.ssafy.fundyou.ui.arcore

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentArCaptureBinding
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.util.extension.getFormattedCurrentTime
import java.io.ByteArrayOutputStream


class ArCaptureFragment : BaseFragment<FragmentArCaptureBinding>(R.layout.fragment_ar_capture) {
    private lateinit var bitmap: Bitmap

    override fun initView() {
        bitmap = getBitmapFromArgs()
        binding.apply {
            ivCapture.setImageBitmap(bitmap)
            btnSave.setOnClickListener {
                uploadFirebase()
            }
        }
    }

    override fun initViewModels() {}

    private fun uploadFirebase() {
        FirebaseApp.initializeApp(requireContext())
        val storage = Firebase.storage

        // Create a storage reference from our app
        val storageRef = storage.reference

        // Create a reference to "mountains.jpg"
        val imgPath = getFormattedCurrentTime() + "suyong.jpg"

        val sofaRef = storageRef.child(imgPath)

        // Create a reference to 'images/mountains.jpg'
        val sofaImagesRef = storageRef.child(imgPath)

        // While the file names are the same, the references point to different files
        sofaRef.name == sofaImagesRef.name // true
        sofaRef.path == sofaImagesRef.path // false

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = sofaRef.putBytes(data)
        uploadTask
        .addOnFailureListener {
            it.printStackTrace()
        }.addOnSuccessListener { taskSnapshot ->
            Toast.makeText(requireContext(), "Image upload Success!!!", Toast.LENGTH_SHORT).show()
            navigate(ArCaptureFragmentDirections.actionArCaptureFragmentToArGalleryFragment())
        }.addOnProgressListener {
                binding.pgUpload.apply {
                    this.visibility = View.VISIBLE
                }
            }.addOnPausedListener {
                Log.d("suyong", "uploadFirebase: Paused")
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun getBitmapFromArgs(): Bitmap {
        val args: ArCaptureFragmentArgs by navArgs()
        return args.arg1
    }
}