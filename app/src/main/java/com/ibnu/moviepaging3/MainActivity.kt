package com.ibnu.moviepaging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.ibnu.moviepaging3.adapter.MovieAdapter
import com.ibnu.moviepaging3.databinding.ActivityMainBinding
import com.ibnu.moviepaging3.di.ViewModelFactory
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val factory = ViewModelFactory.getInstance()
        ViewModelProvider(this, factory)[MovieViewModel::class.java]
    }

    private lateinit var movieAdapter: MovieAdapter

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        initiateRecyclerview()
        initiateData()
    }


    private fun initiateRecyclerview() {
        movieAdapter = MovieAdapter()
        movieAdapter.addLoadStateListener { loadState ->
            if (loadState.append.endOfPaginationReached) {
                if (movieAdapter.itemCount < 1) {
                    print("kosong")
                }
            }
            binding.progressBarMovie.isVisible = loadState.refresh is LoadState.Loading
        }
        with(binding.rvMovie) {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        }
    }

    private fun initiateData() {
        viewModel.getTopRatedMovie().observe(this, Observer {
            movieAdapter.submitData(lifecycle, it)
        })
    }
}