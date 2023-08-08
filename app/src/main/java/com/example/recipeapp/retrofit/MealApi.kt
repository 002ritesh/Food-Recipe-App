package com.example.recipeapp.retrofit

import com.example.recipeapp.pojo.CategoryList
import com.example.recipeapp.pojo.MealByCategory
import com.example.recipeapp.pojo.MealByCategoryList
import com.example.recipeapp.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    //it is use to get random meal api
    //https://www.themealdb.com/api/json/v1/1/random.php
    @GET("random.php")
    fun getRandomMeal(): Call<MealList>



    //https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772
    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id: String): Call<MealList>



    //this api for category of popular list item
    //https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood
    @GET("filter.php?")
    fun getPopularItem(@Query("c") categoryName: String): Call<MealByCategoryList>



    //https://www.themealdb.com/api/json/v1/1/categories.php
    @GET("categories.php")
    fun getCategories(): Call<CategoryList>


    //www.themealdb.com/api/json/v1/1/filter.php?c=Seafood
    @GET("filter.php")
    fun getMealByCategory(@Query("c") categoryName: String): Call<MealByCategoryList>


}