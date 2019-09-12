package com.android.moviestest.ui.genres


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.android.moviestest.R
import com.android.moviestest.api.ApiResponse
import com.android.moviestest.data.genre.GenreList
import com.android.moviestest.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_genre_list.*
import kotlinx.android.synthetic.main.layout_error.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class GenreListFragment : BaseFragment(), GenreListViewModel.GenreListListener {

    private var adapter: GenreListAdapter? = null
    private val viewModel: GenreListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_genre_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.attachViewModel(this, this)

        adapter = GenreListAdapter(mutableListOf())
        recyclerView.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        recyclerView.adapter = adapter

        viewModel.getGenreList()

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getGenreList()
        }
    }

    override fun onBackPressed() {
        showToast("Press Back")
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

    override fun onSuccessGetGenreList(data: GenreList) {
        viewAnimator.displayedChild = VIEW_SUCCESS
        swipeRefreshLayout.isRefreshing = false
        data.genres?.toMutableList()?.let {
            adapter?.data = it
            adapter?.notifyDataSetChanged()
        }
    }
}
