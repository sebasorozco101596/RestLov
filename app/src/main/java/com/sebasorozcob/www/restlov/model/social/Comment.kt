package com.sebasorozcob.www.restlov.model.social

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sebasorozcob.www.restlov.model.customer.Customer
import com.sebasorozcob.www.restlov.model.RestaurantItem
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Comment(
    @SerializedName("id")
    val id: Long,
    @SerializedName("feed")
    val feed: Long,
    @SerializedName("customer")
    val customer: Customer?,
    @SerializedName("restaurant")
    val restaurant: @RawValue RestaurantItem?,
    @SerializedName("text")
    val text: String,
    @SerializedName("parent")
    val parent: Comment?,
    @SerializedName("numberOfChildrenComments")
    val numberOfChildrenComments: Int?
) : Parcelable