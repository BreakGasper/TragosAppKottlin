package com.breakgasper.tragosappkottlin.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

//pojo
@Parcelize
data class Drink(
    @SerializedName("idDrink")
    val idTrago: String,
    @SerializedName("strDrinkThumb")
    val imagen: String = "",
    @SerializedName("strDrink")
    val nombre: String = "",
    @SerializedName("strInstructions")
    val descrpcion: String = "",
    @SerializedName("strAlcoholic")
    val hasAlcohol: String = "Non_Alcoholic"
): Parcelable

data class DrinkList(
    @SerializedName("drinks")
    val drinkList : List<Drink>
    )

@Entity(tableName = "tragosEntity")
data class DrinkEntity(
    @PrimaryKey//(autoGenerate = true)
    val idTrago: String,
    @ColumnInfo(name = "trago_imagen")
    val imagen: String = "",
    @ColumnInfo(name ="trago_nombre")
    val nombre: String = "",
    @ColumnInfo(name ="trago_descripcion")
    val descrpcion: String = "",
    @ColumnInfo(name ="trago_has_alcohol")
    val hasAlcohol: String = "Non_Alcoholic"
)


