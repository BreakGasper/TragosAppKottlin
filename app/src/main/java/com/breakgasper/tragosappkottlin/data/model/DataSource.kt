package com.breakgasper.tragosappkottlin.data.model

import com.breakgasper.tragosappkottlin.AppDataBase
import com.breakgasper.tragosappkottlin.vo.Resource
import com.breakgasper.tragosappkottlin.vo.RetrofitClient

class DataSource(private val appDataBase: AppDataBase) {
    suspend fun  getTragoByName(tragoName:String): Resource<List<Drink>>{
        return Resource.Success(RetrofitClient.webservice.getTragooByName(tragoName).drinkList)
    }

    suspend fun insertTragoIntoRoom(trago:DrinkEntity){
        appDataBase.tragoDao().insertFavorite(trago)
    }

   suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>> {
        return Resource.Success(appDataBase.tragoDao().getAllFavoriteDrinks())

    }
//    suspend fun deleteDrink(drink: Drink){
//        appDataBase.tragoDao().deleteDrink(drink)
//    }
    /*  val generateTragosList = Resource.Success(listOf(
          Drink("https://www.recetasderechupete.com/wp-content/uploads/2020/06/Margarita.jpg","Margarita","Con azucar y nueces"),
          Drink("https://m.media-amazon.com/images/I/71qJcIFJviL._AC_SX569_.jpg","Fernet","Fernet con coca y 2 hielos"),
          Drink("https://picsum.photos/200","Toro","Toro con pritty"),
          Drink("https://picsum.photos/200","Gancia","Gancia con sprite")
      ))
  */
}