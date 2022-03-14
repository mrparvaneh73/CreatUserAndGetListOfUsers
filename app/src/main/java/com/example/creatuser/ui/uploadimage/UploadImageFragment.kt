package com.example.creatuser.ui.uploadimage

import android.app.AlertDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.creatuser.R
import com.example.creatuser.databinding.FragmentUploadimageBinding
import com.example.creatuser.di.App
import com.example.creatuser.ui.users.UserFragmentFactory
import com.example.creatuser.ui.users.UsersFragmentViewModel
import java.io.ByteArrayOutputStream

class UploadImageFragment : Fragment(R.layout.fragment_uploadimage) {
    var imageByteArray: ByteArray? = null
    private val args by navArgs<UploadImageFragmentArgs>()
    private val viewmodel by viewModels<UploadImageFragmentViewModel>(factoryProducer = {
        UploadImageFragmentFactory((requireActivity().application as App).serviceLocator.repository)
    })
    private val cameraluncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            imageByteArray = it.toByteArray()
            binding.profileImage.setImageBitmap(it)
        }
    private val selectPictureLuncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            val change = context?.contentResolver?.openInputStream(it)?.readBytes()
            imageByteArray = change
            binding.profileImage.setImageURI(it)
        }
    lateinit var binding: FragmentUploadimageBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUploadimageBinding.bind(view)

        changeprofile()
        upload()

    }

    private fun upload() {
        binding.upload.setOnClickListener {
            viewmodel.uploadImage(args.id, imageByteArray!!)
            viewmodel.response.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
            viewmodel.error.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun changeprofile() {
        binding.changeprofile.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Selectimage")
            builder.setMessage("Choose your Option:")
            builder.setPositiveButton("Gallery") { dialog, which ->
                dialog.dismiss()
                selectPictureLuncher.launch("image/*")
            }
            builder.setNegativeButton("Cammera") { dialog, which ->
                dialog.dismiss()
                cameraluncher.launch(null)
            }
            builder.create().show()
        }
    }

    fun Bitmap.toByteArray(): ByteArray {
        ByteArrayOutputStream().apply {
            compress(Bitmap.CompressFormat.JPEG, 10, this)
            return toByteArray()
        }
    }
}