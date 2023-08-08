package com.example.recipeapp.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ActivityMealBinding
import com.example.recipeapp.db.MealDatebase
import com.example.recipeapp.fragments.HomeFragment
import com.example.recipeapp.pojo.Meal
import com.example.recipeapp.viewModel.MealViewModel
import com.example.recipeapp.viewModel.MealViewModelFactory
import java.net.URL

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String

    private lateinit var mealMvvm: MealViewModel

    private lateinit var youTubeLink: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mealDatebase = MealDatebase.getInstance(this)
        val viewModelFactory = MealViewModelFactory(mealDatebase)
        mealMvvm = ViewModelProvider(this, viewModelFactory)[MealViewModel::class.java]

        getMealInformationFromIntent()

        setInformationInView()

        loadingCase()

        mealMvvm.getMealDetail(mealId)
        observerMealDetailLiveData()

        onYouTubeImageClick()
        onFavoriteClick()
    }

    private fun onFavoriteClick() {
        binding.floatingActionButton.setOnClickListener {
            mealToSave?.let {
                mealMvvm.insertMeal(it)
                Toast.makeText(this, "Meal Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onYouTubeImageClick() {
        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youTubeLink))
            startActivity(intent)
        }
    }

    private var mealToSave: Meal? = null

    private fun observerMealDetailLiveData() {
        mealMvvm.observeMealDetailLiveData().observe(this, object : Observer<Meal>{
            override fun onChanged(value: Meal) {
                onResponseCase()
                val meal = value

                //save favorite image
                mealToSave = meal

                binding.tvCategory.text = "Category : ${meal.strCategory}"
                binding.tvArea.text = "Area : ${meal.strArea}"
                binding.tvInstractionDetails.text = meal.strInstructions

                youTubeLink = meal.strYoutube.toString()
            }

        })
    }

    private fun setInformationInView() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)

        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun loadingCase(){
        binding.progressBar.visibility = View.VISIBLE
        binding.floatingActionButton.visibility = View.INVISIBLE
        binding.tvInstraction.visibility = View.INVISIBLE
        binding.tvCategory.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE
    }
    private fun onResponseCase(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.floatingActionButton.visibility = View.VISIBLE
        binding.tvInstraction.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }

}