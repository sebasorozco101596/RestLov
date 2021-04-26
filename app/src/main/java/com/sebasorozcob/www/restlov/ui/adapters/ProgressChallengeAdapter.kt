package com.sebasorozcob.www.restlov.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sebasorozcob.www.restlov.R
import com.sebasorozcob.www.restlov.databinding.ChallengesRowLayoutBinding
import com.sebasorozcob.www.restlov.model.purchases.ChallengeProgress
import com.sebasorozcob.www.restlov.util.Constants
import com.sebasorozcob.www.restlov.util.RestaurantsDiffUtil
import kotlin.math.roundToInt

class ProgressChallengeAdapter : RecyclerView.Adapter<ProgressChallengeAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ChallengesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var challenges = emptyList<ChallengeProgress>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ChallengesRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentChallenge = challenges[position]
        with(holder) {
            binding.restaurantImageView.load(currentChallenge.restaurant?.imageUrl)
            binding.restaurantNameTextView.text = currentChallenge.restaurant?.name
            binding.challengeDescriptionTextView.text = currentChallenge.challenge.description
            binding.challengeProgressBar.progress = currentChallenge.progress!!
            val percent = currentChallenge.progress.toString() + "% completed, "
            binding.percentProgressTextView.text = percent
            binding.priceMissingTextView.text = findMissingPrice(
                currentChallenge.challenge.type,
                currentChallenge.challenge.quota.toDouble(),
                currentChallenge.progress
            )
            if (currentChallenge.challenge.validity == null) {
                binding.validityTextView.text =
                    binding.root.context.getString(R.string.no_expiration_date)
            } else {
                val validity =
                    "You have up to ${currentChallenge.challenge.validity} for complete this challenge"
                binding.validityTextView.text = validity
            }
        }
    }

    private fun findMissingPrice(
        type: String,
        challenge: Double,
        progress: Int
    ): String? {

        var missing: String? = null
        val miss: Double = (challenge * (progress.toDouble() / 100))
        if (type == Constants.ChallengeType.PRICE.toString()) {
            val minus = challenge - miss
            missing = "$${minus.roundToInt()} for complete the challenge"
        } else if (type == Constants.ChallengeType.SALES.toString()) {
            val minus = challenge - miss
            missing = "${minus.toInt()} sales for complete the challenge"
        }

        return missing
    }

    override fun getItemCount(): Int = challenges.size

    fun setData(newData: List<ChallengeProgress>) {
        val menuItemsDiffUtil = RestaurantsDiffUtil(challenges, newData)
        val diffUtilResult = DiffUtil.calculateDiff(menuItemsDiffUtil)
        challenges = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}