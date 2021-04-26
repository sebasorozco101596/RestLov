package com.sebasorozcob.www.restlov.ui.adapters.feeds

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.sebasorozcob.www.restlov.databinding.FeedsRowLayoutBinding
import com.sebasorozcob.www.restlov.model.social.Comment
import com.sebasorozcob.www.restlov.model.social.Feed
import com.sebasorozcob.www.restlov.ui.adapters.PagerAdapter
import com.sebasorozcob.www.restlov.ui.fragments.main.feeds.CommentsFeedFragment
import com.sebasorozcob.www.restlov.ui.fragments.main.feeds.ViewPagerItemFragment
import com.sebasorozcob.www.restlov.util.RestaurantsDiffUtil

class FeedsAdapter(val context: Context, private val fragmentActivity: FragmentActivity) :
    RecyclerView.Adapter<FeedsAdapter.MyViewHolder>() {

    var feedItems = emptyList<Feed>()

    class MyViewHolder(val binding: FeedsRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            FeedsRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentFeed = feedItems[position]

        with(holder) {
            with(binding) {
                restaurantNameTextView.text = currentFeed.restaurant.name
                feedDescriptionTextView.text = currentFeed.description

                val numberOfLikes = when (currentFeed.numberOfLikes) {
                    1 -> "${currentFeed.numberOfLikes} Love"
                    else -> "${currentFeed.numberOfLikes} Loves"
                }

                numberOfLikesTextView.text = numberOfLikes

                val photos = currentFeed.photos
                if (!photos.isNullOrEmpty()) {
                    //setUpGridView(photosGridView, currentFeed.photos)
                    setUpTabLayout(currentFeed.photos, binding)

                } else {
                    photosGridView.visibility = View.GONE
                }
                hearthImageView.setOnClickListener {
                    Toast.makeText(binding.root.context, "Make an animation", Toast.LENGTH_SHORT)
                        .show()
                }
                feedCommentImageView.setOnClickListener {
                    val intent = Intent(context, CommentsFeedFragment::class.java)
                    // Later i need to send the comments to the activity
                    // and add a appBar to the activity for come back to the feed
                    context.startActivity(intent)
                }

                numberOfCommentsTextView.setOnClickListener {
                    val intent = Intent(context, CommentsFeedFragment::class.java)
                    context.startActivity(intent)
                }

                if (!currentFeed.comments.isNullOrEmpty() && currentFeed.comments.size > 0) {
                    val numberOfComments = "${findNumberOfComments(currentFeed.comments)} comments..."
                    numberOfCommentsTextView.text = numberOfComments
                }
                //addCommentEditText.text
            }
        }
    }

    private fun findNumberOfComments(comments: ArrayList<Comment>?): Int {
        val number = comments?.size

        return number!!
    }

    private fun setUpTabLayout(photos: List<String>, binding: FeedsRowLayoutBinding) {

        if (photos.size == 1) {
            binding.tabLayout.visibility = View.GONE
        }

        val fragments: ArrayList<Fragment> = ArrayList()
        val images: ArrayList<String> = photos as ArrayList<String>

        val resultBundle = Bundle()
        resultBundle.putStringArrayList("photos", photos)

        val pagerAdapter = PagerAdapter(resultBundle, fragments, fragmentActivity)
        binding.viewPager2.adapter = pagerAdapter

        for (feedImage in images) {
            val fragment: ViewPagerItemFragment =
                ViewPagerItemFragment.getInstance(feedImage, images.indexOf(feedImage))
            fragments.add(fragment)
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { _, _ ->
        }.attach()
    }

    override fun getItemCount(): Int = feedItems.size

    fun setData(newData: List<Feed>) {
        val feedsDiffUtil = RestaurantsDiffUtil(feedItems, newData)
        val diffUtilResult = DiffUtil.calculateDiff(feedsDiffUtil)
        feedItems = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}