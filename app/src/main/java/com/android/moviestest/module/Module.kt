package com.android.moviestest.module

import com.android.moviestest.ui.base.BaseViewModel
import com.android.moviestest.ui.detailmovie.MovieDetailViewModel
import com.android.moviestest.ui.genres.GenreListViewModel
import com.android.moviestest.ui.movies.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Module {

    val injectionModule = module {

        viewModel { BaseViewModel() }
        viewModel { GenreListViewModel() }
        viewModel { MovieListViewModel() }
        viewModel { MovieDetailViewModel() }
    }

}
