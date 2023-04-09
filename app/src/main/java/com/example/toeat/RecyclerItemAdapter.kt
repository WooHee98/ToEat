package com.example.toeat

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.toeat.databinding.ItemRecipeBinding

class RecyclerItemAdapter: RecyclerView.Adapter<RecyclerItemAdapter.MyViewHolder>() {

    private var recipeList = arrayListOf<RecipeValue>()

    inner class MyViewHolder(val binding: ItemRecipeBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val listItemBinding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.recipeList = recipeList[position]
        holder.itemView.setOnClickListener {
            Toast.makeText(it.context, recipeList[position].name, Toast.LENGTH_SHORT).show()
            val intent = Intent(it.context, RecipeDetailActivity::class.java)
            intent.putExtra("recipeList", recipeList[position])
            Log.d("test1", recipeList[position].toString())
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<RecipeValue>) {
        recipeList = list
        notifyDataSetChanged()
    }

}