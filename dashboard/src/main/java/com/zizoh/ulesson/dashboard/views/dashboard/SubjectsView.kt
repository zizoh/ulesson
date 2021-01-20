package com.zizoh.ulesson.dashboard.views.dashboard

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.zizoh.ulesson.core.ext.dpToPx
import com.zizoh.ulesson.dashboard.R
import com.zizoh.ulesson.dashboard.databinding.LayoutSubjectsBinding
import com.zizoh.ulesson.dashboard.navigation.NavigationDispatcher
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewIntent
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewState.SubjectsViewState
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.SubjectViewIntent
import com.zizoh.ulesson.dashboard.ui.dashboard.adapter.SubjectAdapter
import com.zizoh.ulesson.dashboard.views.SpacingItemDecoration
import com.zizoh.ulesson.presentation.mvi.MVIView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by zizoh on 16/January/2021.
 */

@AndroidEntryPoint
class SubjectsView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet),
    MVIView<DashboardViewIntent, SubjectsViewState> {

    @Inject
    lateinit var subjectAdapter: SubjectAdapter

    @Inject
    lateinit var navigator: Provider<NavigationDispatcher>

    private var binding: LayoutSubjectsBinding

    init {
        isSaveEnabled = true
        val inflater: LayoutInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        binding = LayoutSubjectsBinding.inflate(inflater, this, true)
        binding.rvSubjects.addItemDecoration(SpacingItemDecoration(context.dpToPx(24), context.dpToPx(4), false, true))
        binding.rvSubjects.adapter = subjectAdapter.apply {
            clickListener = navigator.get()::openSubjectFragment
        }
    }

    fun retryIntent(): Flow<DashboardViewIntent> =
        binding.errorState.clicks.map {
            SubjectViewIntent.LoadSubjects
        }

    override fun render(state: SubjectsViewState) {
        when (state) {
            SubjectsViewState.Loading -> {
                with(binding) {
                    rvSubjects.isVisible = false
                    errorState.isVisible = false
                    progressBar.isVisible = true
                }
            }
            is SubjectsViewState.SubjectsLoaded -> {
                with(binding) {
                    progressBar.isVisible = false
                    errorState.isVisible = false
                    rvSubjects.isVisible = true
                }
                subjectAdapter.submitList(state.subjects)
            }
            SubjectsViewState.SubjectsEmpty -> {
                with(binding) {
                    rvSubjects.isVisible = false
                    progressBar.isVisible = false
                    errorState.isVisible = true
                    errorState.setTitle(context.getString(R.string.no_subjects_title))
                    errorState.setCaption(context.getString(R.string.no_subjects_caption))
                    errorState.isButtonVisible = false
                }
            }
            is SubjectsViewState.Error -> {
                with(binding) {
                    rvSubjects.isVisible = false
                    progressBar.isVisible = false
                    errorState.isVisible = true
                    errorState.setTitle(context.getString(R.string.an_error_occurred))
                    errorState.setCaption(state.message)
                    errorState.isButtonVisible = true
                }
            }
        }
    }

    override val intents: Flow<DashboardViewIntent>
        get() = flowOf()
}
