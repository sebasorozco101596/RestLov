package com.sebasorozcob.www.restlov.model.purchases

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sebasorozcob.www.restlov.model.Product
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.*

@Parcelize
data class Purchase(
    @SerializedName("restaurant")
    val restaurant: String,
    @SerializedName("customer")
    val customer: String,
    @SerializedName("date")
    val date: Date,
    @SerializedName("products")
    val products: @RawValue List<Product>,
    @SerializedName("total")
    val total: Double
) : Parcelable