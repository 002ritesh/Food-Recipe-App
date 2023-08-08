package com.example.recipeapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.adapters.CategoryMealAdapter
import com.example.recipeapp.databinding.ActivityCategoryMealsBinding
import com.example.recipeapp.databinding.CategoryItemBinding
import com.example.recipeapp.fragments.HomeFragment
import com.example.recipeapp.viewModel.CategoryMealViewModel

class CategoryMealsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryMealsBinding
    lateinit var categoryMealsViewModel: CategoryMealViewModel
    private lateinit var catetogoryMealAdapter: CategoryMealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()

        categoryMealsViewModel = ViewModelProvider(this)[CategoryMealViewModel::class.java]

        categoryMealsViewModel.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)

        categoryMealsViewModel.observerMealLiveData().observe(this, Observer { mealsList ->
            binding.tvCategoryCount.text = mealsList.size.toString()
            catetogoryMealAdapter.setMealsList(mealsList)
        })
    }

    private fun prepareRecyclerView() {
        catetogoryMealAdapter = CategoryMealAdapter()
        binding.tvMeal.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = catetogoryMealAdapter
        }
    }
}