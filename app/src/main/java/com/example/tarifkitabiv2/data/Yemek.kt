package com.example.tarifkitabiv2.data

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "yemek_table")
class Yemek(@PrimaryKey(autoGenerate = true)
            val id:Int,
            @ColumnInfo("isim")
            val isim:String,
            @ColumnInfo("tarif")
            val tarif:String,
            @ColumnInfo("tür")
            val tur:String,
            @ColumnInfo("yemekFoto")
            val yemekFoto:Bitmap
):Parcelable {
}