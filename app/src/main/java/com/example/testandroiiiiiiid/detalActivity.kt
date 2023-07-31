package com.example.testandroiiiiiiid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testandroiiiiiiid.databinding.ActivityDetalBinding
private lateinit var binding:ActivityDetalBinding
class detalActivity: AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}