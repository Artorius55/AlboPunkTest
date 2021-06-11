package com.arthur.albo.punktest.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "beers")
data class Beer(
    @PrimaryKey
    val id:Int,
    val name: String?,
    val tagline: String?,
    @SerializedName("first_brewed")
    val firstBrewed:String?,
    val description:String?,
    @SerializedName("image_url")
    val imageUrl:String?,
    val abv:Double,
    val ibu:Double,
    val ph:Double,
    @SerializedName("brewers_tips")
    val brewerTips: String?
    ) : Parcelable
