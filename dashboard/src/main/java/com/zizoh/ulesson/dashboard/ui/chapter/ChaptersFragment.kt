package com.zizoh.ulesson.dashboard.ui.chapter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.zizoh.ulesson.core.ext.observe
import com.zizoh.ulesson.core.view_binding.viewBinding
import com.zizoh.ulesson.dashboard.R
import com.zizoh.ulesson.dashboard.databinding.FragmentChaptersBinding
import com.zizoh.ulesson.dashboard.presentation.chapter.ChapterViewModel
import com.zizoh.ulesson.dashboard.presentation.chapter.mvi.ChapterViewIntent
import com.zizoh.ulesson.dashboard.presentation.chapter.mvi.ChapterViewIntent.LoadChapters
import com.zizoh.ulesson.dashboard.presentation.chapter.mvi.ChapterViewState
import com.zizoh.ulesson.presentation.mvi.MVIView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.merge

/**
 * Created by zizoh on 19/January/2021.
 */

@AndroidEntryPoint
class ChaptersFragment : Fragment(R.layout.fragment_chapters),
    MVIView<ChapterViewIntent, ChapterViewState> {

    private val viewModel: ChapterViewModel by viewModels()

    private val binding: FragmentChaptersBinding by viewBinding(FragmentChaptersBinding::bind)

    private val args: ChaptersFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            loadChaptersForSubject.offer(LoadChapters(args.subjectId))
        }
    }
    private val loadChaptersForSubject =
        ConflatedBroadcastChannel<LoadChapters>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.processIntent(intents)
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    override fun render(state: ChapterViewState) {
        binding.chapters.render(state)
    }

    override val intents: Flow<ChapterViewIntent>
        get() = merge(
            loadChaptersForSubject.asFlow(),
            binding.chapters.intents,
            binding.chapters.retryIntent(args.subjectId)
        )

}