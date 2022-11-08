package com.breakgasper.tragosappkottlin.domain

import androidx.room.*
import com.breakgasper.tragosappkottlin.data.model.Drink
import com.breakgasper.tragosappkottlin.data.model.DrinkEntity

@Dao
interface TragosDAO {

    @Query("SELECT * FROM tragosEntity")
    suspend fun getAllFavoriteDrinks():List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(trago:DrinkEntity)

//    @Delete
//    suspend fun deleteDrink(drink: Drink)
}