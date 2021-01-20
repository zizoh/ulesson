package com.zizoh.ulesson.dashboard.views.chapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.zizoh.ulesson.dashboard.R
import com.zizoh.ulesson.dashboard.databinding.LayoutChaptersBinding
import com.zizoh.ulesson.dashboard.navigation.NavigationDispatcher
import com.zizoh.ulesson.dashboard.presentation.chapter.mvi.ChapterViewIntent
import com.zizoh.ulesson.dashboard.presentation.chapter.mvi.ChapterViewState
import com.zizoh.ulesson.dashboard.ui.chapter.adapter.ChapterAdapter
import com.zizoh.ulesson.presentation.mvi.MVIView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by zizoh on 19/January/2021.
 */

@AndroidEntryPoint
class ChaptersView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet),
    MVIView<ChapterViewIntent, ChapterViewState> {

    @Inject
    lateinit var chapterAdapter: ChapterAdapter

    @Inject
    lateinit var navigator: Provider<NavigationDispatcher>

    private var binding: LayoutChaptersBinding

    init {
        isSaveEnabled = true
        val inflater: LayoutInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        binding = LayoutChaptersBinding.inflate(inflater, this, true)
        binding.rvChapters.adapter = chapterAdapter.apply {
            clickListener = navigator.get()::openVideoFragmentFromChapterFragment
        }
        binding.tvChapter.setOnClickListener {
            navigator.get().goBack()
        }
    }

    private val saveWatchedTopic: Flow<ChapterViewIntent>
        get() = chapterAdapter.clicks
            .onEach(navigator.get()::openVideoFragmentFromChapterFragment)
            .map(ChapterViewIntent::SaveWatchedTopic)

    fun retryIntent(subjectId: Int): Flow<ChapterViewIntent> =
        binding.chaptersErrorState.clicks.map {
            ChapterViewIntent.LoadChapters(subjectId)
        }

    override fun render(state: ChapterViewState) {
        when (state) {
            ChapterViewState.Idle -> {

            }
            ChapterViewState.Loading -> {
                with(binding) {
                    rvChapters.isVisible = false
                    chaptersErrorState.isVisible = false
                    chaptersProgressBar.isVisible = true
                }
            }
            is ChapterViewState.ChaptersLoaded -> {
                with(binding) {
                    chaptersProgressBar.isVisible = false
                    chaptersErrorState.isVisible = false
                    tvChapter.isVisible = true
                    tvChapter.text = state.chapters[0].subjectName
                    rvChapters.isVisible = true
                }
                chapterAdapter.submitList(state.chapters)
            }
            is ChapterViewState.Error -> {
                with(binding) {
                    rvChapters.isVisible = false
                    chaptersProgressBar.isVisible = false
                    chaptersErrorState.isVisible = true
                    chaptersErrorState.setTitle(context.getString(R.string.an_error_occurred))
                    chaptersErrorState.setCaption(state.message)
                    chaptersErrorState.isButtonVisible = true
                }
            }
        }
    }

    override val intents: Flow<ChapterViewIntent>
        get() = saveWatchedTopic
}
