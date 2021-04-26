package com.sebasorozcob.www.restlov.ui.fragments.main.feeds

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebasorozcob.www.restlov.databinding.FragmentFeedCommentsBinding
import com.sebasorozcob.www.restlov.model.Location
import com.sebasorozcob.www.restlov.model.RestaurantItem
import com.sebasorozcob.www.restlov.model.social.Comment
import com.sebasorozcob.www.restlov.ui.adapters.feeds.FeedCommentsAdapter
import com.sebasorozcob.www.restlov.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFeedFragment : AppCompatActivity() {

    private lateinit var binding: FragmentFeedCommentsBinding

    private val mAdapter by lazy { FeedCommentsAdapter() }
    private var comments = ArrayList<Comment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFeedCommentsBinding.inflate(layoutInflater)
        fillComments()
        setupRecyclerView()
        mAdapter.setData(comments)
        setContentView(binding.root)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        //showShimmerEffect()
    }

    private fun fillComments() {

        val location = Location(
            "43 maple av",
            "Dover",
            "United States",
            "12awd",
            129129,
            129129,
            "New Jersey",
            0,
            "07801"
        )
        val restaurant1 = RestaurantItem(
            Constants.Currency.Dollar.toString(),
            "This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer ",
            null,
            "1129",
            "https://imagenesnoticias.canalrcn.com/ImgNoticias/gastronomia_colombiana_master_chef.jpg",
            location,
            null,
            "Sebas Delicias",
            "973 465 7855",
            3,
            0,
            null
        )

        val comment1 =
            Comment(
                1,
                1,
                null,
                restaurant1,
                "The food on this restaurant is the best on the area",
                null,
                2
            )
        val comment2 =
            Comment(
                1,
                1,
                null, restaurant1,
                "Here you can eat all you can",
                null,
                2
            )

        comments.add(comment1)
        comments.add(comment2)
    }

}