package com.example.testandroiiiiiiid

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toolbar
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.example.testandroiiiiiiid.Retrofit.RetrofitService
import com.example.testandroiiiiiiid.VIewModel.ReviewsViewModel
import com.example.testandroiiiiiiid.VIewModel.ReviewsViewModelFactory
import com.example.testandroiiiiiiid.databinding.ActivityMainBinding

@SuppressLint("StaticFieldLeak")
private lateinit var binding:ActivityMainBinding
private var check:Boolean = false
private var check1:Boolean = false
@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace<reviews>(R.id.frame)
            .commit()
            binding.toolbar.critics.setOnClickListener {
            binding.toolbar.critics.setTextColor(Color.parseColor("#b5e2fa"))
            binding.toolbar.reviewes.setTextColor(Color.WHITE)
            binding.toolbar.reviewes.setBackgroundResource(R.drawable.rounded_left_1)
            binding.toolbar.critics.setBackgroundResource(R.drawable.rounded_right_1)
            binding.toolbar.toolbarr.setBackgroundResource(R.color.orr1)
            val windows = window
            check = false
                windows.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                windows.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                windows.statusBarColor = this.resources.getColor(R.color.orr1)
                supportFragmentManager.beginTransaction()
                    .replace<criticFragment>(R.id.frame)
                    .addToBackStack("First tab")
                    .commit() }
            binding.toolbar.reviewes.setOnClickListener {
            binding.toolbar.critics.setTextColor(Color.WHITE)
            binding.toolbar.reviewes.setTextColor(Color.parseColor("#F7A072"))
            binding.toolbar.toolbarr.setBackgroundResource(R.color.orr)
            binding.toolbar.reviewes.setBackgroundResource(R.drawable.rounded_left)
            binding.toolbar.critics.setBackgroundResource(R.drawable.rounded_right)
            check1 = false
            val windows = window
            windows.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            windows.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            windows.setStatusBarColor(this.resources.getColor(R.color.orr))
            supportFragmentManager.beginTransaction()
                .replace<reviews>(R.id.frame)
                .addToBackStack("Second tab")
                .commit()
        }
    }
}