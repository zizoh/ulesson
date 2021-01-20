package com.zizoh.ulesson.dashboard.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zizoh.ulesson.core.ext.observe
import com.zizoh.ulesson.core.view_binding.viewBinding
import com.zizoh.ulesson.dashboard.R
import com.zizoh.ulesson.dashboard.databinding.FragmentDashboardBinding
import com.zizoh.ulesson.dashboard.presentation.dashboard.DashboardViewModel
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewIntent
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewState
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.RecentTopicsViewIntent
import com.zizoh.ulesson.presentation.mvi.MVIView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import reactivecircus.flowbinding.android.view.clicks

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
            binding.subjects.retryIntent(),
            loadAllWatchedTopicsIntent
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.processIntent(intents)
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private val loadAllWatchedTopicsIntent: Flow<DashboardViewIntent>
        get() = binding.btnMoreRecentTopics.clicks()
            .debounce(2000)
            .map {
                if (binding.btnMoreRecentTopics.text == getString(R.string.see_less)) {
                    RecentTopicsViewIntent.LoadMostRecentTopics
                } else {
                    RecentTopicsViewIntent.LoadAllRecentTopics
                }
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
                when (state) {
                    is DashboardViewState.RecentTopicsViewState.MostRecentWatchedTopicsLoaded -> {
                        if (state.lessons.size >= 3) {
                            binding.btnMoreRecentTopics.text = getString(R.string.view_all)
                            binding.btnMoreRecentTopics.isVisible = true
                        }
                    }
                    DashboardViewState.RecentTopicsViewState.RecentTopicsEmpty -> {
                        binding.btnMoreRecentTopics.isVisible = false
                    }
                    is DashboardViewState.RecentTopicsViewState.AllWatchedTopicsLoaded -> {
                        binding.btnMoreRecentTopics.text = getString(R.string.see_less)
                        binding.btnMoreRecentTopics.isVisible = true
                    }
                    else -> {
                        binding.btnMoreRecentTopics.isVisible = false
                    }
                }
            }
        }
    }
}