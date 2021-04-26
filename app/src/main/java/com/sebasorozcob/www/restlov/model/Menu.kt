package com.sebasorozcob.www.restlov.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Menu(
    @SerializedName("_id")
    val id: String,
    @SerializedName("restaurantId")
    val restaurantId: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("typeProducts")
    val typeProducts: @RawValue List<TypeProduct>,
    @SerializedName("__v")
    val v: Int
) : Parcelable