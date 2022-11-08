package com.breakgasper.tragosappkottlin.domain

import com.breakgasper.tragosappkottlin.data.model.Drink
import com.breakgasper.tragosappkottlin.data.model.DrinkEntity
import com.breakgasper.tragosappkottlin.vo.Resource

interface Repo {
   suspend fun getTragosList(tragoName:String) : Resource<List<Drink>>
   suspend fun geTragosFavoritos(): Resource<List<DrinkEntity>>
   suspend fun insertTrago(trago:DrinkEntity)
//   suspend fun deleteDrink(drink: Drink)
}