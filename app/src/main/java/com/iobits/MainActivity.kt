package com.iobits

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.iobits.databinding.ActivityMainBinding
import com.iobits.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //observer user
        userViewModel.users.onEach {
            if (it.isNotEmpty())
                binding.tvUserInfo.text = it.first().name
        }


        // save user
        binding.btnSaveUser.setOnClickListener {

        }

        // read user
        binding.btnReadUser.setOnClickListener {
        }
    }
}