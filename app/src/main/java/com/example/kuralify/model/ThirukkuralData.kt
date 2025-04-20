package com.example.kuralify.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ThirukkuralData(
    @SerializedName("ID") val id : Int,
    @SerializedName("Adhigaram_ID") val Adhigaram_id : String,
    @SerializedName("Paal") val paal : String,
    @SerializedName("Iyal") val iyal : String,
    @SerializedName("Adhigaram") val adhigaram : String,
    @SerializedName("Kural") val kural : String,
    @SerializedName("Transliteration") val transliteration : String,
    @SerializedName("Vilakam") val explanation : String,
    @SerializedName("Couplet") val couplet : String,

): Parcelable