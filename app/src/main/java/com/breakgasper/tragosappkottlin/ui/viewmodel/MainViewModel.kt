package com.breakgasper.tragosappkottlin.ui.viewmodel

import androidx.lifecycle.*
import com.breakgasper.tragosappkottlin.data.model.Drink
import com.breakgasper.tragosappkottlin.data.model.DrinkEntity
import com.breakgasper.tragosappkottlin.domain.Repo
import com.breakgasper.tragosappkottlin.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repo) : ViewModel() {

    private val tragosDataa = MutableLiveData<String>()
    fun setTrago(tragoName: String) {
        tragosDataa.value = tragoName
    }
    init {
        setTrago("margarita")
    }
    val fetchTragosList = tragosDataa.distinctUntilChanged().switchMap { nombreTrago ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getTragosList(nombreTrago))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    fun guardarTrago(trago:DrinkEntity){
        viewModelScope.launch {
            repo.insertTrago(trago)
        }
    }

    fun getTragosFavoritos() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.geTragosFavoritos())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun deleteDrink(drink: Drink) {
        viewModelScope.launch {
//            repo.deleteDrink(drink)
        }
    }
}