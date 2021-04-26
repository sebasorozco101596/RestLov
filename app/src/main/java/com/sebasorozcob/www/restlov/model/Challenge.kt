package com.sebasorozcob.www.restlov.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sebasorozcob.www.restlov.util.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
data class Challenge(
    @SerializedName("quota")
    val quota: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("validity")
    val validity: String?
) : Parcelable