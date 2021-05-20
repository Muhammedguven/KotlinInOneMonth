package com.muhammedguven.besinlerkitabi.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.muhammedguven.besinlerkitabi.model.Food

@Dao
interface FoodDao {
    @Insert
    suspend fun insertAll(vararg food: Food): List<Long>

    @Query("SELECT * FROM food")
    suspend fun getAllFoods(): List<Food>

    @Query("SELECT * FROM food WHERE uuid= :Id")
    suspend fun getFood(Id: Int): Food

    @Query("DELETE FROM food")
    suspend fun deleteAllFoods()
}