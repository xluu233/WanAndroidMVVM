package com.xlu.wanandroidmvvm.ui.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xlu.wanandroidmvvm.R

class ProjectFragment : Fragment() {

    private lateinit var projectViewModel: ProjectViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        projectViewModel =
                ViewModelProviders.of(this).get(ProjectViewModel::class.java)
        return inflater.inflate(R.layout.fragment_project, container, false)

    }
}