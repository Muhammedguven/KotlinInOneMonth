package com.muhammedguven.besinlerkitabi.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.muhammedguven.besinlerkitabi.model.Food
import com.muhammedguven.besinlerkitabi.service.FoodDatabase
import kotlinx.coroutines.launch

class FoodDetailViewModel(application: Application) : BaseViewModel(application) {
    val foodLiveData = MutableLiveData<Food>()
    fun getFood(uuid: Int) {
        launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            val food = dao.getFood(uuid)
            foodLiveData.value = food
        }
    }
}