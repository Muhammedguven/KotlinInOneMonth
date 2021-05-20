package com.muhammedguven.besinlerkitabi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.muhammedguven.besinlerkitabi.model.Food
import com.muhammedguven.besinlerkitabi.service.FoodApiService
import com.muhammedguven.besinlerkitabi.service.FoodDatabase
import com.muhammedguven.besinlerkitabi.util.SpecialSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FoodListViewModel(application: Application) : BaseViewModel(application) {
    val foods = MutableLiveData<List<Food>>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodLoading = MutableLiveData<Boolean>()
    private var updatingTime = 10 * 60 * 1000 * 1000 * 1000L

    private val foodApiService = FoodApiService()
    private val disposable = CompositeDisposable()

    private val specialSharedPreferences = SpecialSharedPreferences(getApplication())
    fun refreshData() {
        val takeTime = specialSharedPreferences.takeTime()
        if (takeTime != null && updatingTime != 0L && System.nanoTime() - takeTime < updatingTime) {
            getDataFromSqlite()
        } else getDataFromInternet()

    }

    fun refreshFromInternet() {
        getDataFromInternet()
    }

    private fun getDataFromSqlite() {
        foodLoading.value = true
        launch {
            val foods = FoodDatabase(getApplication()).foodDao().getAllFoods()
            showFoods(foods)
            Toast.makeText(getApplication(), "Data are coming from SQLite", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun getDataFromInternet() {
        foodLoading.value = true
        disposable.add(
            foodApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Food>>() {
                    override fun onSuccess(t: List<Food>) {
                        saveToSqlite(t)
                        Toast.makeText(
                            getApplication(),
                            "Data are coming from Internet",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onError(e: Throwable) {
                        foodErrorMessage.value = true
                        foodLoading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun showFoods(foodsList: List<Food>) {
        foods.value = foodsList
        foodErrorMessage.value = false
        foodLoading.value = false
    }

    private fun saveToSqlite(foodsList: List<Food>) {
        launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            dao.deleteAllFoods()
            val uuidList = dao.insertAll(*foodsList.toTypedArray())
            var i = 0
            while (i < foodsList.size) {
                foodsList[i].uuid = uuidList[i].toInt()
                i++
            }
            showFoods(foodsList)
        }
        specialSharedPreferences.saveTime(System.nanoTime())
    }
}