package com.example.testandroiiiiiiid


import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.text.DateIntervalFormat
import android.os.Bundle
import android.os.Handler
import android.text.format.DateUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.util.Pair
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testandroiiiiiiid.Retrofit.RetrofitService
import com.example.testandroiiiiiiid.VIewModel.ReviewsViewModel
import com.example.testandroiiiiiiid.VIewModel.ReviewsViewModelFactory
import com.example.testandroiiiiiiid.adapter.loadStateAdapter
import com.example.testandroiiiiiiid.adapter.rewiewAdapter
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class reviews : Fragment() {
    lateinit var adapter: rewiewAdapter
    lateinit var search:SearchView
    companion object {
        fun newInstance() = reviews()
    }
    lateinit var viewModel: ReviewsViewModel
    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reviews, container, false)

  //      val dataClick = view.findViewById<ConstraintLayout>(R.id.dataclick)
 //       val datatext = view.findViewById<TextView>(R.id.dataTExt)
//        dataClick.setOnClickListener {
//            val picker = MaterialDatePicker.Builder.dateRangePicker()
//                .setTheme(R.style.AppTheme)
//                .setTitleText("SelectDataPicker")
//                .setSelection(Pair(null,null))
//                .build()
//            picker.show(this.childFragmentManager,"Tag")
//            picker.addOnPositiveButtonClickListener{
//                datatext.text = convertdata(it.first)+convertdata(it.second)
//            }
//                lifecycleScope.launch {
//                    delay(1000)
//                    viewModel.af1(datatext.text.toString()).collectLatest {
//                        adapter.submitData(it)
//                    }
//                }
  //      }


        val refresh = view.findViewById<SwipeRefreshLayout>(R.id.swipeContainer)
        refresh.setOnRefreshListener{
            adapter.refresh()
            val recyclerView:RecyclerView= view!!.findViewById(R.id.rcview)
            adapter =rewiewAdapter()
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = loadStateAdapter { adapter.retry() },
                footer = loadStateAdapter { adapter.retry() })
            recyclerView.setHasFixedSize(true)
            refresh?.isRefreshing = false
            setupView()
        }
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
        viewModel = activity?.let { ViewModelProvider(it.viewModelStore, factory) }?.get(ReviewsViewModel::class.java) ?: viewModel
    }
    private fun setupView() {
        lifecycleScope.launch {
            viewModel.reviews.collectLatest {
                adapter.submitData(it)
            }

        }

    }
    fun convertdata(time:Long):String{
        val utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        utc.timeInMillis= time
        val format = SimpleDateFormat("dd/MM/yyyy",Locale.getDefault())
        return format.format(utc.time)
    }

}









