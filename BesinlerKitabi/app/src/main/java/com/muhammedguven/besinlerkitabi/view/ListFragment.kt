package com.muhammedguven.besinlerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammedguven.besinlerkitabi.R
import com.muhammedguven.besinlerkitabi.adapter.FoodRecyclerAdapter
import com.muhammedguven.besinlerkitabi.viewmodel.FoodListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    private lateinit var viewModel: FoodListViewModel
    private val recyclerFoodAdapter = FoodRecyclerAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FoodListViewModel::class.java)
        viewModel.refreshData()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerFoodAdapter
        swipeRefreshLayout.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            errorMessage.visibility = View.GONE
            loadingBar.visibility = View.VISIBLE
            viewModel.refreshFromInternet()
            swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData()

    }

    fun observeLiveData() {
        viewModel.foods.observe(viewLifecycleOwner, Observer {
            it?.let {
                recyclerView.visibility = View.VISIBLE
                recyclerFoodAdapter.updateFoodList(it)
            }
        })
        viewModel.foodErrorMessage.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    errorMessage.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                } else errorMessage.visibility = View.GONE
            }
        })
        viewModel.foodLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    recyclerView.visibility = View.GONE
                    errorMessage.visibility = View.GONE
                    loadingBar.visibility = View.VISIBLE
                } else {
                    loadingBar.visibility = View.GONE
                }
            }
        })
    }

}