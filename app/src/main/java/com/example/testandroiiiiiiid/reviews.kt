package com.example.testandroiiiiiiid


import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testandroiiiiiiid.Retrofit.RetrofitService
import com.example.testandroiiiiiiid.VIewModel.ReviewsViewModel
import com.example.testandroiiiiiiid.VIewModel.ReviewsViewModelFactory
import com.example.testandroiiiiiiid.adapter.loadStateAdapter
import com.example.testandroiiiiiiid.adapter.rewiewAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
class reviews : Fragment() {
    lateinit var adapter: rewiewAdapter
    lateinit var search:SearchView
    companion object {
        fun newInstance() = reviews()
    }
    lateinit var viewModel: ReviewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reviews, container, false)
        search = view.findViewById(R.id.search)
        search.setOnQueryTextListener(object :OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                lifecycleScope.launch {
                    delay(1000)
                  viewModel.af(newText).collectLatest {
                      adapter.submitData(it)
                  }
                }
              return true
            }
        })
        val recyclerView:RecyclerView= view!!.findViewById(R.id.rcview)
        adapter =rewiewAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = loadStateAdapter { adapter.retry() },
            footer = loadStateAdapter { adapter.retry() })
        recyclerView.setHasFixedSize(true)
        setupViewModel()
        setupView()
        return view
    }
    private fun setupViewModel() {
        val factory = ReviewsViewModelFactory(RetrofitService.getInstance())
        viewModel =  ViewModelProvider(activity!!.viewModelStore, factory).get(ReviewsViewModel::class.java)
    }
    private fun setupView() {
        lifecycleScope.launch {
            viewModel.reviews.collectLatest {
                adapter.submitData(it)
            }

        }

    }

}









