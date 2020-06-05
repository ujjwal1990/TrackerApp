package com.uv.mytracker.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.uv.mytracker.R
import kotlinx.android.synthetic.main.fragment_base.view.*

open class BaseFragment : Fragment() {

    open fun getLayoutResourceId(): Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_base, container, false)
        val content = inflater.inflate(getLayoutResourceId(), container, false)
        rootView.main_layout.addView(content)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This callback will only be called when MyFragment is at least Started.
//        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
//            // Handle the back button event
//            activity?.showToast("Hello")
//        }

        // The callback can be enabled or disabled here or in the lambda
    }
}