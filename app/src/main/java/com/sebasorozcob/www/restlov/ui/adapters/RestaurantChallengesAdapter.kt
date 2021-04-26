package com.sebasorozcob.www.restlov.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sebasorozcob.www.restlov.R
import com.sebasorozcob.www.restlov.databinding.RestaurantChallengesRowLayoutBinding
import com.sebasorozcob.www.restlov.model.Challenge
import com.sebasorozcob.www.restlov.util.RestaurantsDiffUtil
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class RestaurantChallengesAdapter :
    RecyclerView.Adapter<RestaurantChallengesAdapter.MyViewHolder>() {

    var restaurantChallenges = emptyList<Challenge>()

    class MyViewHolder(val binding: RestaurantChallengesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RestaurantChallengesRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRestaurantChallenge = restaurantChallenges[position]

        with(holder) {
            binding.challengeDescriptionTextView.text = currentRestaurantChallenge.description

            if (currentRestaurantChallenge.validity == null) {
                binding.validityTextView.text =
                    binding.root.context.getString(R.string.no_expiration_date)
            } else {

                val inputFormatter: DateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
                val outputFormatter: DateTimeFormatter =
                    DateTimeFormatter.ofPattern("EEE, d MMM yyy", Locale.ENGLISH)
                val date: LocalDate = LocalDate.parse(currentRestaurantChallenge.validity, inputFormatter)
                val formattedDate: String = outputFormatter.format(date)

                val validity =
                    "You have up to $formattedDate midnight, for complete this challenge"
                binding.validityTextView.text = validity
            }

            binding.startChallengeButton.setOnClickListener {
                // TODO Make the onClickListener of start the challenge here.
            }
        }
    }

    override fun getItemCount(): Int = restaurantChallenges.size

    fun setData(newData: List<Challenge>) {
        val menuItemsDiffUtil = RestaurantsDiffUtil(restaurantChallenges, newData)
        val diffUtilResult = DiffUtil.calculateDiff(menuItemsDiffUtil)
        restaurantChallenges = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

}
