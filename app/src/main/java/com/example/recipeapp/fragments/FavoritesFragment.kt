package com.example.recipeapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.activities.MainActivity
import com.example.recipeapp.adapters.FavoritesMealAdapter
import com.example.recipeapp.databinding.FragmentFavoritesBinding
import com.example.recipeapp.viewModel.HomeViewModel


class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var favoritesMealAdapter: FavoritesMealAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerview()
        observeFavorites()

    }

    private fun prepareRecyclerview() {
        favoritesMealAdapter = FavoritesMealAdapter()
        binding.rvFavorites.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = favoritesMealAdapter
        }
    }

    private fun observeFavorites() {
        viewModel.observerFavoritesMealsLiveDate().observe(requireActivity(), Observer {
            meals ->
            meals.forEach {
                Log.d("test", it.idMeal)
            }
            /*favoritesMealAdapter.differ.submitList(meals)*/

        })
    }

}