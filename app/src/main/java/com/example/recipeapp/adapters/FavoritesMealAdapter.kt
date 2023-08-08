package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.MealItemBinding
import com.example.recipeapp.pojo.Meal

class FavoritesMealAdapter : RecyclerView.Adapter<FavoritesMealAdapter.FavoriteMealViewHoolder>() {

    class FavoriteMealViewHoolder(val binding: MealItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    private val difUtil = object : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, difUtil)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMealViewHoolder {
        return FavoriteMealViewHoolder(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FavoriteMealViewHoolder, position: Int) {
        val meal = differ.currentList[position]
        Glide.with(holder.itemView)
            .load(meal.strMealThumb)
            .into(holder.binding.imgMeal)

        holder.binding.titleMeal.text = meal.strMeal
    }
}