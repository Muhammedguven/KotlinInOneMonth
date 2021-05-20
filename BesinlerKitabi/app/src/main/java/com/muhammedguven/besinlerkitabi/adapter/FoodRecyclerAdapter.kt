package com.muhammedguven.besinlerkitabi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.muhammedguven.besinlerkitabi.R
import com.muhammedguven.besinlerkitabi.databinding.FoodItemBinding
import com.muhammedguven.besinlerkitabi.model.Food
import com.muhammedguven.besinlerkitabi.view.ListFragmentDirections
import kotlinx.android.synthetic.main.food_item.view.*
import java.util.*

class FoodRecyclerAdapter(val foodList: ArrayList<Food>) :
    RecyclerView.Adapter<FoodRecyclerAdapter.FoodViewHolder>(), FoodClickListener {
    class FoodViewHolder(var view : FoodItemBinding) : RecyclerView.ViewHolder(view.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.food_item, parent, false)
        val view =
            DataBindingUtil.inflate<FoodItemBinding>(inflater, R.layout.food_item, parent, false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.view.food = foodList[position]
        holder.view.listener = this
            /* holder.itemView.foodName.text = foodList[position].foodName
             holder.itemView.foodCalorie.text = foodList[position].foodCalorie
             holder.itemView.imageView.downloadImage(
                 foodList.get(position).foodImage,
                 makePlaceHolder(holder.itemView.context)
             )
             holder.itemView.setOnClickListener {
                 val action =
                     ListFragmentDirections.actionListFragmentToDetailFragment(foodList[position].uuid)
                 Navigation.findNavController(it).navigate(action)
             }*/
        }

        fun updateFoodList(newList: List<Food>) {
            foodList.clear()
            foodList.addAll(newList)
            notifyDataSetChanged()
        }

    override fun clicked(view: View) {
        val uuid= view.food_uuid.text.toString().toIntOrNull()
        if (uuid != null) {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(uuid)
            Navigation.findNavController(view).navigate(action)
        }
    }
}