package com.sebasorozcob.www.restlov.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sebasorozcob.www.restlov.databinding.RestaurantsRowLayoutBinding
import com.sebasorozcob.www.restlov.model.RestaurantItem
import com.sebasorozcob.www.restlov.util.RestaurantsDiffUtil

class RestaurantsAdapter : RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder>() {

    private var restaurants = emptyList<RestaurantItem>()

    class MyViewHolder(val binding: RestaurantsRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RestaurantsRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRestaurant = restaurants[position]
        with(holder.binding) {
            restaurant = currentRestaurant
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = restaurants.size

    fun setData(newData: com.sebasorozcob.www.restlov.model.Restaurant) {
        val recipesDiffUtil = RestaurantsDiffUtil(restaurants, newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        restaurants = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}