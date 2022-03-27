package com.example.assignment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelProvider.AndroidViewModelFactory(application)
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter by lazy {
        ClosedPrAdapter(emptyList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUi()
        viewModel.getPrDetails()
        observeData()
    }

    private fun observeData() {
        viewModel.submitDetailLiveData.observe(this) {
            when(it) {
                is ViewState.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
                is ViewState.Success -> {
                    binding.progress.visibility = View.INVISIBLE
                    adapter.updateList(it.data)
                }
                is ViewState.Error -> {
                    binding.progress.visibility = View.INVISIBLE
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initUi() {
        with(binding) {
            rvRecycler.layoutManager = LinearLayoutManager(this@MainActivity)
            rvRecycler.adapter = adapter
            refreshButton.setOnClickListener {
                viewModel.getPrDetails()
            }
        }
    }
}