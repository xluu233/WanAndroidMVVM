package com.xlu.wanandroidmvvm.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xlu.wanandroidmvvm.R

class NavigationFragment : Fragment() {

    private lateinit var navigationViewModel: NavigationViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        navigationViewModel =
                ViewModelProviders.of(this).get(NavigationViewModel::class.java)
        return inflater.inflate(R.layout.fragment_navigation, container, false)

    }
}