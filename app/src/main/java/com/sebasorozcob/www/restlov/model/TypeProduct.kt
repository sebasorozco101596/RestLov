package com.sebasorozcob.www.restlov.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class TypeProduct(
    @SerializedName("_id")
    val id: String,
    @SerializedName("information")
    val information: String?,
    @SerializedName("products")
    val products: @RawValue List<Product>,
    @SerializedName("type")
    val type: String,
    @SerializedName("__v")
    val v: Int
) : Parcelable