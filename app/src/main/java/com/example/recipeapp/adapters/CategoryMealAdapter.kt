package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.MealItemBinding
import com.example.recipeapp.pojo.MealByCategory


class CategoryMealAdapter() : RecyclerView.Adapter<CategoryMealAdapter.CategoryMealViewHolder>() {

    private var mealsList = ArrayList<MealByCategory>()

    fun setMealsList(mealList: List<MealByCategory>) {
        this.mealsList = mealList as ArrayList<MealByCategory>
        notifyDataSetChanged()
    }

    class CategoryMealViewHolder(val binding: MealItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealViewHolder {
        return CategoryMealViewHolder(MealItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: CategoryMealViewHolder, position: Int) {
        Glide.with(holder.itemView).load(mealsList[position].strMealThumb).into(holder.binding.imgMeal)
        holder.binding.titleMeal.text = mealsList[position].strMeal
    }

}