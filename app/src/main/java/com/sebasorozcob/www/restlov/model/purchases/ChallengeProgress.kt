package com.sebasorozcob.www.restlov.model.purchases

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sebasorozcob.www.restlov.model.Challenge
import com.sebasorozcob.www.restlov.model.customer.Customer
import com.sebasorozcob.www.restlov.model.RestaurantItem
import com.sebasorozcob.www.restlov.util.Constants
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ChallengeProgress(
    @SerializedName("restaurant")
    val restaurant: @RawValue RestaurantItem?,
    @SerializedName("customer")
    val customer: Customer?,
    @SerializedName("challenge")
    val challenge: @RawValue Challenge,
    @SerializedName("progress")
    val progress: Int?
) : Parcelable