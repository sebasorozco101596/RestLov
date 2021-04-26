package com.sebasorozcob.www.restlov.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import coil.load
import com.sebasorozcob.www.restlov.databinding.DialogPhotoFeedBinding

class PhotoFeedDialog : DialogFragment() {

    var _binding: DialogPhotoFeedBinding? = null
    val binding get() = _binding!!

    private var photo: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogPhotoFeedBinding.inflate(inflater, container, false)
        setUpView(binding)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setUpView(binding: DialogPhotoFeedBinding) {

        photo = arguments?.getString(KEY_DIALOG)
        binding.photoFeedImageView.load(photo)
    }

    companion object {

        const val KEY_DIALOG = "PhotoFeedDialog"

        fun newInstance(
            photo: String
        ): PhotoFeedDialog {
            val args = Bundle()
            args.putString(KEY_DIALOG, photo)
            val fragment = PhotoFeedDialog()
            fragment.arguments = args
            return fragment
        }
    }
}