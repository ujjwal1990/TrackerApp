package com.uv.mytracker.base

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.uv.mytracker.R
import kotlinx.android.synthetic.main.base_bottomsheet_layout.*
import kotlinx.android.synthetic.main.base_bottomsheet_layout.view.*

open class BaseBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.base_bottomsheet_layout, container, false)
        val content = inflater.inflate(layoutResourceId(), container, false)
//        progressBarView = rootView.progressBarFragment
        rootView.bottom_sheet.addView(content)
        return rootView
    }

    open fun shouldShowFullScreenDialog() = true
    open fun layoutResourceId(): Int = 0

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return if (shouldShowFullScreenDialog()) Dialog(requireContext(), R.style.CustomDialogTheme)
        else {
            val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
            dialog.setOnShowListener {
//                val bottomSheetDialog = it as BottomSheetDialog
                setupFullHeight()
            }
            dialog
        }
    }

    private fun setupFullHeight() {
        val behavior = BottomSheetBehavior.from(bottom_sheet)
        val layoutParams = bottom_sheet.layoutParams
        val windowHeight = getWindowHeight()
        if (layoutParams != null) {
            layoutParams.height = windowHeight
        }
        bottom_sheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        activity?.let {
            it.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
            return displayMetrics.heightPixels
        }
        return displayMetrics.heightPixels
    }
}