package com.example.testandroiiiiiiid


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
class reviews : Fragment() {

    lateinit var adapter: rewiewAdapter
    companion object {
        fun newInstance() = reviews()
    }
    lateinit var viewModel: ReviewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reviews, container, false)

        return view
    }
    private fun setupViewModel() {
        val factory = ReviewsViewModelFactory(RetrofitService.getInstance())
        viewModel = ViewModelProvider(this, factory).get(ReviewsViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val recyclerView:RecyclerView= view!!.findViewById(R.id.rcview)


        adapter =rewiewAdapter()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = loadStateAdapter { adapter.retry() },
            footer = loadStateAdapter { adapter.retry() })
        recyclerView.setHasFixedSize(true)
        setupViewModel()
        setupView()

    }
    private fun setupView() {
        lifecycleScope.launch {
            viewModel.reviews.collectLatest {
                adapter.submitData(it)
            }

        }

    }

}









