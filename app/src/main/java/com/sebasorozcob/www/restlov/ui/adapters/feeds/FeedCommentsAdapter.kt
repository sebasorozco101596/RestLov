package com.sebasorozcob.www.restlov.ui.adapters.feeds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sebasorozcob.www.restlov.databinding.FeedCommentsRowLayoutBinding
import com.sebasorozcob.www.restlov.model.Location
import com.sebasorozcob.www.restlov.model.RestaurantItem
import com.sebasorozcob.www.restlov.model.social.Comment
import com.sebasorozcob.www.restlov.util.Constants
import com.sebasorozcob.www.restlov.util.RestaurantsDiffUtil

class FeedCommentsAdapter : RecyclerView.Adapter<FeedCommentsAdapter.MyViewHolder>() {

    private val mAdapter by lazy { ChildsCommentAdapter() }
    var comments = emptyList<Comment>()

    class MyViewHolder(val binding: FeedCommentsRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            FeedCommentsRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentComment = comments[position]

        with(holder) {
            with(binding) {
                mainCommentTextView.text = currentComment.text

                if (currentComment.numberOfChildrenComments!! > 0) {
                    setupRecyclerView(this)
                    mAdapter.setData(findChildrenComments(currentComment))
                }
            }
        }
    }

    private fun findChildrenComments(commentParent: Comment): List<Comment> {
        val childrenComments = ArrayList<Comment>()

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
            Comment(1, 1, null, restaurant1, "I am complete sure of that", commentParent, null)
        val comment2 =
            Comment(1, 1, null, restaurant1, "Yeah i am agree", commentParent, null)


        childrenComments.add(comment1)
        childrenComments.add(comment2)

        return childrenComments
    }

    override fun getItemCount(): Int = comments.size

    fun setData(newData: List<Comment>) {
        val menuItemsDiffUtil = RestaurantsDiffUtil(comments, newData)
        val diffUtilResult = DiffUtil.calculateDiff(menuItemsDiffUtil)
        comments = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

    private fun setupRecyclerView(binding: FeedCommentsRowLayoutBinding) {
        binding.childCommentsRecyclerView.adapter = mAdapter
        binding.childCommentsRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        //showShimmerEffect()
    }
}
