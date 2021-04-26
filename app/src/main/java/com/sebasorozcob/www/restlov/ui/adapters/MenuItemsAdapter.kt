package com.sebasorozcob.www.restlov.ui.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sebasorozcob.www.restlov.databinding.MenuRowLayoutBinding
import com.sebasorozcob.www.restlov.model.Product
import com.sebasorozcob.www.restlov.util.RestaurantsDiffUtil

class MenuItemsAdapter : RecyclerView.Adapter<MenuItemsAdapter.MyViewHolder>() {

    private var menuItems = emptyList<Product>()

    class MyViewHolder(val binding: MenuRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            MenuRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentMenuItem = menuItems[position]
        Log.d("MenuItemsAdapter", currentMenuItem.toString())
        with(holder) {
            binding.productImageView.load(currentMenuItem.imageUrl)
            binding.priceProductTextView.text = "$ ${currentMenuItem.price}"
            binding.productNameTextView.text = currentMenuItem.name
            binding.productDescriptionTextView.text = currentMenuItem.description
        }
    }

    override fun getItemCount(): Int = menuItems.size

    // Los totales estan al contrario

    fun setData(newData: List<Product>) {
        val menuItemsDiffUtil = RestaurantsDiffUtil(menuItems, newData)
        val diffUtilResult = DiffUtil.calculateDiff(menuItemsDiffUtil)
        menuItems = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}