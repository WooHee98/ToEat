package com.example.toeat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseData(
    @SerializedName(value = "COOKRCP01")
    val response: ResponseInfo
)

data class ResponseInfo(
    val row: ArrayList<RecipeValue>
)

data class RecipeValue(
    @SerializedName(value = "RCP_NM")
    var name: String?,

    @SerializedName(value = "RCP_PARTS_DTLS")
    var material: String?,

    @SerializedName(value = "MANUAL01")
    var manual01: String?,

    @SerializedName(value = "MANUAL_IMG01")
    var manual01_img: String?,

    @SerializedName(value = "MANUAL02")
    var manual02: String?,

    @SerializedName(value = "MANUAL_IMG02")
    var manual02_img: String?,

    @SerializedName(value = "MANUAL03")
    var manual03: String?,

    @SerializedName(value = "MANUAL_IMG03")
    var manual03_img: String?,

    @SerializedName(value = "ATT_FILE_NO_MAIN")
    var thumbNail: String?,
): Serializable


@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "number")
    var number: String,
)