package com.example.toeat

import retrofit2.Call
import retrofit2.http.GET

interface RecipeApi {

    @GET("bb2d36bdcc3b4bd69401/COOKRCP01/json/1/30")
    fun getRecipeData(): Call<ResponseData>

}