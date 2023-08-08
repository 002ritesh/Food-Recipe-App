package com.example.recipeapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeapp.pojo.MealByCategory
import com.example.recipeapp.pojo.MealByCategoryList
import com.example.recipeapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealViewModel : ViewModel() {

    val mealLiveData = MutableLiveData<List<MealByCategory>>()

    fun getMealsByCategory(categoryName: String) {
        RetrofitInstance.api.getMealByCategory(categoryName).enqueue(object : Callback<MealByCategoryList>{
            override fun onResponse(
                call: Call<MealByCategoryList>,
                response: Response<MealByCategoryList>
            ) {
               response.body()?.let { mealsList ->
                   mealLiveData.postValue(mealsList.meals)
               }
            }

            override fun onFailure(call: Call<MealByCategoryList>, t: Throwable) {
                Log.e(" CategoryMealsViewModel", t.message.toString() )
            }
        })
    }

    fun observerMealLiveData(): LiveData<List<MealByCategory>>{
        return mealLiveData
    }

}