package com.example.toeat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.toeat.databinding.ActivityRecipeDetailBinding

class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeDetailBinding
    private lateinit var recipeList:RecipeValue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        recipeList = intent.getSerializableExtra("recipeList") as RecipeValue

        supportActionBar?.title = recipeList.name
        binding.recipeList = recipeList

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}