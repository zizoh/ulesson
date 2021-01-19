package com.zizoh.ulesson.dashboard.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zizoh.ulesson.core.ext.observe
import com.zizoh.ulesson.core.view_binding.viewBinding
import com.zizoh.ulesson.dashboard.R
import com.zizoh.ulesson.dashboard.databinding.FragmentDashboardBinding
import com.zizoh.ulesson.dashboard.presentation.dashboard.DashboardViewModel
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewIntent
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewState
import com.zizoh.ulesson.presentation.mvi.MVIView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge

/**
 * Created by zizoh on 16/January/2021.
 */

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard),
    MVIView<DashboardViewIntent, DashboardViewState> {

    private val viewModel: DashboardViewModel by viewModels()

    private val binding: FragmentDashboardBinding by viewBinding(FragmentDashboardBinding::bind)

    override val intents: Flow<DashboardViewIntent>
        get() = merge(
            binding.subjects.intents,
            binding.subjects.retryIntent()
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.processIntent(intents)
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    override fun render(state: DashboardViewState) {
        when (state) {
            DashboardViewState.Idle -> {

            }
            is DashboardViewState.SubjectsViewState -> {
                binding.subjects.render(state)
            }
            is DashboardViewState.RecentTopicsViewState -> {
                binding.recentTopics.render(state)
            }
        }
    }
}