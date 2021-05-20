package com.muhammedguven.besinlerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.muhammedguven.besinlerkitabi.R
import com.muhammedguven.besinlerkitabi.databinding.FragmentDetailBinding
import com.muhammedguven.besinlerkitabi.viewmodel.FoodDetailViewModel

class DetailFragment : Fragment() {
    private lateinit var dataBinding: FragmentDetailBinding
    private lateinit var viewModel: FoodDetailViewModel
    private var foodId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
        // inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            foodId = DetailFragmentArgs.fromBundle(it).besinId
        }
        viewModel = ViewModelProviders.of(this).get(FoodDetailViewModel::class.java)
        viewModel.getFood(foodId)
        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.foodLiveData.observe(viewLifecycleOwner, Observer { food ->
            food?.let {
                dataBinding.selectedFood = it
               /* foodName.text = it.foodName
                foodCalorie.text = it.foodCalorie
                foodCarbohydrate.text = it.foodCarbohydrate
                foodProtein.text = it.foodProtein
                foodOil.text = it.foodOil
                context?.let {
                    foodImage.downloadImage(food.foodImage, makePlaceHolder(it))
                }
                */

            }
        })
    }
}