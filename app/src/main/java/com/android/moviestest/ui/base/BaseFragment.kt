package com.android.moviestest.ui.base

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.moviestest.api.ApiResponse

abstract class BaseFragment : Fragment(), BaseViewModel.BaseListener {

    val VIEW_LOADING = 0
    val VIEW_ERROR = 1
    val VIEW_SUCCESS = 2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachOnBackPressed()
    }

    private fun attachOnBackPressed() {
        view?.isFocusableInTouchMode = true
        view?.requestFocus()
        view?.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                onBackPressed()
            }
            true
        }
    }

    open fun onBackPressed() {
        activity?.onBackPressed()
    }

    open fun showToast(message: String?){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onLoading(isLoading: Boolean) {

    }

    override fun onFailure(message: String) {
        showToast(message)
    }

    override fun onError(error: ApiResponse) {
        showToast(error.statusMessage)
    }

}
