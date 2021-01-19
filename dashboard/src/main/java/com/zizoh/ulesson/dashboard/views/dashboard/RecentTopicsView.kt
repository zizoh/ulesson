package com.zizoh.ulesson.dashboard.views.dashboard

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.zizoh.ulesson.dashboard.R
import com.zizoh.ulesson.dashboard.databinding.LayoutRecentTopicsBinding
import com.zizoh.ulesson.dashboard.navigation.NavigationDispatcher
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewIntent
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewState.RecentTopicsViewState
import com.zizoh.ulesson.dashboard.ui.dashboard.adapter.WatchedTopicAdapter
import com.zizoh.ulesson.presentation.mvi.MVIView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by zizoh on 16/January/2021.
 */

@AndroidEntryPoint
class RecentTopicsView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet),
    MVIView<DashboardViewIntent, RecentTopicsViewState> {

    @Inject
    lateinit var topicAdapter: WatchedTopicAdapter

    @Inject
    lateinit var navigator: Provider<NavigationDispatcher>

    private var binding: LayoutRecentTopicsBinding

    init {
        isSaveEnabled = true
        val inflater: LayoutInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        binding = LayoutRecentTopicsBinding.inflate(inflater, this, true)
        binding.rvRecentTopics.adapter = topicAdapter.apply {
            clickListener = navigator.get()::openVideoFragment
        }
    }

    override fun render(state: RecentTopicsViewState) {
        when (state) {
            RecentTopicsViewState.LoadingMostRecentWatchedTopics -> {
                with(binding) {
                    recentTopicsErrorState.isVisible = false
                    rvRecentTopics.isVisible = false
                    recentTopicsProgressBar.isVisible = true
                }
            }
            is RecentTopicsViewState.MostRecentWatchedTopicsLoaded -> {
                with(binding) {
                    recentTopicsProgressBar.isVisible = false
                    recentTopicsErrorState.isVisible = false
                    rvRecentTopics.isVisible = true
                }
                topicAdapter.submitList(state.lessons)
            }
            RecentTopicsViewState.RecentTopicsEmpty -> {
                with(binding) {
                    recentTopicsProgressBar.isVisible = false
                    rvRecentTopics.isVisible = false
                    recentTopicsErrorState.isVisible = false
                    recentTopicsErrorState.setTitle(context.getString(R.string.no_recently_watched_topics_title))
                    recentTopicsErrorState.setCaption(context.getString(R.string.no_recently_watched_topics_caption))
                    recentTopicsErrorState.isButtonVisible = false
                }
            }
            RecentTopicsViewState.LoadingAllWatchedTopics -> {

            }
            is RecentTopicsViewState.AllWatchedTopicsLoaded -> {
                with(binding) {
                    recentTopicsProgressBar.isVisible = false
                    recentTopicsErrorState.isVisible = false
                    rvRecentTopics.isVisible = true
                }
                topicAdapter.submitList(state.lessons)
            }
        }
    }

    override val intents: Flow<DashboardViewIntent>
        get() = flowOf()
}
