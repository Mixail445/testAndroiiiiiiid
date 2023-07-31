package com.example.testandroiiiiiiid

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toolbar
import com.example.testandroiiiiiiid.databinding.ActivityMainBinding

@SuppressLint("StaticFieldLeak")
private lateinit var binding:ActivityMainBinding
class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, reviews.newInstance())
                .commitNow()
        }
        binding.toolbar.critics.setOnClickListener {

         //   binding.toolbar.critics.setBackgroundColor(R.color.black)
            val windows = window
            windows.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            windows.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            windows.setStatusBarColor(this.resources.getColor(R.color.orr1))

            supportFragmentManager.beginTransaction()
                .replace(R.id.frame,criticFragment.newInstance())
                .commitNow()

        }
        binding.toolbar.reviewes.setOnClickListener {
           // binding.toolbar.critics.setBackgroundColor(R.color.black)
            binding.toolbar.toolbarr.setBackgroundColor(R.color.orr)
            val windows = window
            windows.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            windows.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            windows.setStatusBarColor(this.resources.getColor(R.color.orr))
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame,reviews.newInstance())
                .commitNow()
        }

    }
}