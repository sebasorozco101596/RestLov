package com.sebasorozcob.www.restlov.model.social

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sebasorozcob.www.restlov.model.RestaurantItem
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Feed(
    @SerializedName("id")
    val id: Long,
    @SerializedName("restaurant")
    val restaurant: @RawValue RestaurantItem,
    @SerializedName("description")
    val description: String,
    @SerializedName("photos")
    val photos: @RawValue List<String>?,
    @SerializedName("numberOfLikes")
    val numberOfLikes: Int?,
    @SerializedName("comments")
    val comments: @RawValue ArrayList<Comment>?
): Parcelable