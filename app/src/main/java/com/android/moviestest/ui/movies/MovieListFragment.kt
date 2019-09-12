package com.android.moviestest.ui.movies


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.android.moviestest.R
import com.android.moviestest.api.ApiResponse
import com.android.moviestest.data.movie.MovieList
import com.android.moviestest.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_genre_list.*
import kotlinx.android.synthetic.main.layout_error.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieListFragment : BaseFragment(), MovieListViewModel.MovieListListener {

    private var adapter: MovieListAdapter? = null
    private val viewModel: MovieListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_genre_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.attachViewModel(this, this)

        if (viewModel.id.value == null) {
            viewModel.id.value = arguments?.getInt("id")
        }

        recyclerView.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        adapter = MovieListAdapter(mutableListOf())
        recyclerView.adapter = adapter

        viewModel.id.value?.let { viewModel.getMovieList(it) }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.id.value?.let { viewModel.getMovieList(it) }
        }
    }

    override fun onFailure(message: String) {
        viewAnimator.displayedChild = VIEW_ERROR
        swipeRefreshLayout.isRefreshing = false
        tvError.text = message
    }

    override fun onError(error: ApiResponse) {
        viewAnimator.displayedChild = VIEW_ERROR
        swipeRefreshLayout.isRefreshing = false
        tvError.text = error.statusMessage
    }

    override fun onSuccessGetMovieList(data: MovieList) {
        viewAnimator.displayedChild = VIEW_SUCCESS
        swipeRefreshLayout.isRefreshing = false

        data.results?.toMutableList()?.let {
            adapter?.data = it
            adapter?.notifyDataSetChanged()
        }

    }
}
