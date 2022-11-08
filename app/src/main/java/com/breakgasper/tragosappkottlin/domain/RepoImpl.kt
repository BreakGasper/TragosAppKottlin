package com.breakgasper.tragosappkottlin.domain

import com.breakgasper.tragosappkottlin.data.model.DataSource
import com.breakgasper.tragosappkottlin.data.model.Drink
import com.breakgasper.tragosappkottlin.data.model.DrinkEntity
import com.breakgasper.tragosappkottlin.vo.Resource

class RepoImpl(private val dataSource: DataSource): Repo{

   suspend override fun getTragosList(tragoName:String): Resource<List<Drink>> {
        return  dataSource.getTragoByName(tragoName)
    }

    override suspend fun geTragosFavoritos(): Resource<List<DrinkEntity>> {
        return dataSource.getTragosFavoritos()
    }

    override suspend fun insertTrago(trago: DrinkEntity) {
        dataSource.insertTragoIntoRoom(trago)
    }

//    override suspend fun deleteDrink(drink: Drink) {
//        dataSource.deleteDrink(drink)
//    }
}