package com.example.recipeapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.DialogFragmentNavigatorDestinationBuilder
import com.example.recipeapp.db.MealDatebase
import com.example.recipeapp.pojo.Category
import com.example.recipeapp.pojo.CategoryList
import com.example.recipeapp.pojo.MealByCategoryList
import com.example.recipeapp.pojo.MealByCategory
import com.example.recipeapp.pojo.Meal
import com.example.recipeapp.pojo.MealList
import com.example.recipeapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    private var mealDatabase: MealDatebase
): ViewModel() {

    private var randomMealLiveData = MutableLiveData<Meal>()

    private var popularItemLiveData = MutableLiveData<List<MealByCategory>>()

    private var categoriesLiveData = MutableLiveData<List<Category>>()

    //favorites section
    private var favoritesMealsLiveData = mealDatabase.mealDao().getAllMeals()


    fun getRandomMeal(){
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null){
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("Home Fragment", t.message.toString())
            }
        })

    }

    fun getPopularItem(){
        RetrofitInstance.api.getPopularItem("Seafood").enqueue(object : Callback<MealByCategoryList>{
            override fun onResponse(call: Call<MealByCategoryList>, response: Response<MealByCategoryList>) {
                if (response.body() != null){
                    popularItemLiveData.value = response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<MealByCategoryList>, t: Throwable) {
                Log.d("HomeFragment ", t.message.toString())
            }

        })
    }

    fun getCategories(){
        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                response.body()?.let { categoryList ->
                    categoriesLiveData.postValue(categoryList.categories)
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d( "HomeViewModel: ", t.message.toString())
            }

        })
    }




    fun observeRandomMealLiveData(): LiveData<Meal>{
        return randomMealLiveData
    }

    fun observePopularItemLiveData(): LiveData<List<MealByCategory>> {
        return popularItemLiveData
    }

    fun observerCategoriesLiveData(): LiveData<List<Category>> {
        return categoriesLiveData
    }

    fun observerFavoritesMealsLiveDate(): LiveData<List<Meal>>{
        return favoritesMealsLiveData
    }
}