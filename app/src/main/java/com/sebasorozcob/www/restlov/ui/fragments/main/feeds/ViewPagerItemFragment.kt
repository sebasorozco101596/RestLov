package com.sebasorozcob.www.restlov.ui.fragments.main.feeds

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.sebasorozcob.www.restlov.databinding.FragmentImagepagerItemBinding

class ViewPagerItemFragment(val position: Int) : Fragment() {

    private var _binding: FragmentImagepagerItemBinding? = null
    val binding get() = _binding!!

    private var image: String? = null

    companion object {
        fun getInstance(image: String?, position: Int): ViewPagerItemFragment {
            val fragment = ViewPagerItemFragment(position)
            if (image != null) {
                val bundle = Bundle()
                bundle.putString("imageFeed", image)
                fragment.arguments = bundle
                //Log.d("PHOTOSS", "${fragment.requireArguments().getString("imageFeed")}")
            }
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            var photos: ArrayList<String>
            photos = requireArguments().getStringArrayList("photos") as ArrayList<String>

            image = photos[position]
            Log.d("PHOTOS","$image")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImagepagerItemBinding.inflate(inflater, container, false)
        //image = arguments?.getString("image")
        binding.photoFeedImageView.load(image)
        return binding.root
    }
}