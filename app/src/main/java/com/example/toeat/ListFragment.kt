package com.example.toeat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.toeat.databinding.FragmentListBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var listAdapter: RecyclerItemAdapter
    private var recipeList = arrayListOf<RecipeValue>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listAdapter = RecyclerItemAdapter()

        binding.recyclerView.adapter = listAdapter
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        load()

    }

    private fun load(){

        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(20000L, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.foodsafetykorea.go.kr/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val api = retrofit.create(RecipeApi::class.java)
        api.getRecipeData().enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                Log.d("결과", "성공 : ${response.raw()}")
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        recipeList = it.response.row
                        listAdapter.setList(recipeList)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Log.d("결과:", "실패 : $t")
            }
        })
    }

}