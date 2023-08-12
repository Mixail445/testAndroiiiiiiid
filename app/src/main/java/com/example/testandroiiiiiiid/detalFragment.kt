package com.example.testandroiiiiiiid

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testandroiiiiiiid.Retrofit.RetrofitService
import com.example.testandroiiiiiiid.VIewModel.ReviewsViewModel
import com.example.testandroiiiiiiid.VIewModel.ReviewsViewModelFactory
import com.example.testandroiiiiiiid.adapter.detalAdapter
import com.example.testandroiiiiiiid.adapter.loadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class detalFragment : Fragment() {
    lateinit var viewModel: ReviewsViewModel
    private var adapter = detalAdapter()
    lateinit var text:TextView
    lateinit var text1:TextView
    lateinit var foto:ImageView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
      setupViewModel()


viewModel._itemAmount.observe(viewLifecycleOwner, Observer {
    viewModel.loadDetailsMovie(it)
    viewModel.detailsMovie.observe(viewLifecycleOwner, Observer {
        lifecycleScope.launch {

            it?.sort_name?.let { it1 ->
                viewModel.load(it1).collectLatest {
                    adapter.submitData(it)
                }
            }

        }
        text.text = it?.seo_name
        text1.text = it?.bio
        context?.applicationContext?.let { it1 ->
            Glide.with(it1)
                .load(it?.multimedia?.resource?.src)
                .placeholder(R.drawable.img_1)
                .error(R.drawable.img_1)
                .into(foto)
        }




    })
})
        viewModel.detailsMovie.value?.let { Log.d("Fff", it.sort_name) }



       val view =  inflater.inflate(R.layout.fragment_detal, container, false)
      foto = view.findViewById(R.id.imageActor1)
        text = view.findViewById(R.id.nameActor1)
      text1 = view.findViewById(R.id.bodyText)
        val recyclerView: RecyclerView = view.findViewById(R.id.Recyclerdetal)


        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = loadStateAdapter { adapter.retry() },
            footer = loadStateAdapter { adapter.retry() })
        recyclerView.setHasFixedSize(true)

        return view
    }


    companion object {

        fun newInstance() = detalFragment()

    }
    private fun setupViewModel() {
        val factory = ReviewsViewModelFactory(RetrofitService.getInstance())
        viewModel =  ViewModelProvider(activity!!.viewModelStore, factory).get(ReviewsViewModel::class.java)
    }


        }



