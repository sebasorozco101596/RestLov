package com.sebasorozcob.www.restlov.ui.adapters.feeds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sebasorozcob.www.restlov.databinding.FeedChildrenCommentsRowLayoutBinding
import com.sebasorozcob.www.restlov.model.social.Comment
import com.sebasorozcob.www.restlov.util.RestaurantsDiffUtil

class ChildsCommentAdapter : RecyclerView.Adapter<ChildsCommentAdapter.MyViewHolder>() {

    private var childrenComments = emptyList<Comment>()

    class MyViewHolder(val binding: FeedChildrenCommentsRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            FeedChildrenCommentsRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentChildrenComment = childrenComments[position]

        with(holder) {
            with(binding) {
                childCommentTextView.text = currentChildrenComment.text
            }
        }

    }

    override fun getItemCount(): Int = childrenComments.size

    fun setData(newData: List<Comment>) {
        val menuItemsDiffUtil = RestaurantsDiffUtil(childrenComments, newData)
        val diffUtilResult = DiffUtil.calculateDiff(menuItemsDiffUtil)
        childrenComments = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}