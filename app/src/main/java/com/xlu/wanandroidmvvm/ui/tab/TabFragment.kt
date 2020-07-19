package com.xlu.wanandroidmvvm.ui.tab

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xlu.wanandroidmvvm.R

class TabFragment : Fragment() {

    companion object {
        fun newInstance() = TabFragment()
    }

    private lateinit var viewModel: TabViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TabViewModel::class.java)
        // TODO: Use the ViewModel
    }

}