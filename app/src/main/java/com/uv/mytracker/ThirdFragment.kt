package com.uv.mytracker

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.uv.mytracker.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_third.*

class ThirdFragment : BaseFragment(){
    override fun getLayoutResourceId() = R.layout.fragment_third

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frag1Btn.setOnClickListener {
            findNavController().navigate(R.id.action_ThirdFragment_to_FirstFragment)
        }
        frag2Btn.setOnClickListener {
            findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
        }
    }
}