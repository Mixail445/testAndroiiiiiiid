package com.example.testandroiiiiiiid

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testandroiiiiiiid.Retrofit.RetrofitService


import com.example.testandroiiiiiiid.VIewModel.ReviewsViewModel
import com.example.testandroiiiiiiid.VIewModel.ReviewsViewModelFactory

import com.example.testandroiiiiiiid.adapter.criticAdapter
import com.example.testandroiiiiiiid.adapter.loadStateAdapter
import com.example.testandroiiiiiiid.dataCritic.Result
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class criticFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private var adapter = criticAdapter()
    private var act = MainActivity()
    lateinit var viewModel: ReviewsViewModel
    companion object {
        fun newInstance() = criticFragment()
    }
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_critic, container, false)
        setupViewModel()
        val refresh = view.findViewById<SwipeRefreshLayout>(R.id.swipeContainer)
        refresh.setOnRefreshListener(this)
        val recyclerView: RecyclerView = view.findViewById(R.id.RCVIEW)
        adapter = criticAdapter()
        adapter.setOnClickListener(object :criticAdapter.OnClickListener{
            override fun onClick(position: Int, model: Result) {
                viewModel.setItemAmount(position)
                adapter.addLoadStateListener {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.frame,detalFragment.newInstance())
                        ?.addToBackStack("name")
                        ?.commit()
                }}})
        setupView()
        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = loadStateAdapter { adapter.retry() },
            footer = loadStateAdapter { adapter.retry() })
        recyclerView.setHasFixedSize(true)
        return view
    }
    private fun setupView() {
        lifecycleScope.launch {
            viewModel.critic.collectLatest {
                adapter.submitData(it)
            }

        }
    }
    private fun setupViewModel() {
        val factory = ReviewsViewModelFactory(RetrofitService.getInstance())
       // viewModel =  ViewModelProvider(activity!!.viewModelStore, factory).get(ReviewsViewModel::class.java)
        viewModel = activity?.let { ViewModelProvider(it.viewModelStore, factory) }?.get(ReviewsViewModel::class.java) ?: viewModel
    }

    override fun onRefresh() {
        TODO("Not yet implemented")
    }

}
