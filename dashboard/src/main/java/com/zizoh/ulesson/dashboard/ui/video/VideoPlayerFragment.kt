package com.zizoh.ulesson.dashboard.ui.video

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.zizoh.ulesson.core.ext.observe
import com.zizoh.ulesson.core.view_binding.viewBinding
import com.zizoh.ulesson.dashboard.R
import com.zizoh.ulesson.dashboard.databinding.FragmentVideoPlayerBinding
import com.zizoh.ulesson.dashboard.presentation.video.VideoPlayerViewModel
import com.zizoh.ulesson.dashboard.presentation.video.mvi.VideoViewIntent
import com.zizoh.ulesson.dashboard.presentation.video.mvi.VideoViewIntent.LoadLesson
import com.zizoh.ulesson.dashboard.presentation.video.mvi.VideoViewState
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
class VideoPlayerFragment : Fragment(R.layout.fragment_video_player),
    MVIView<VideoViewIntent, VideoViewState> {

    private val viewModel: VideoPlayerViewModel by viewModels()

    private val binding: FragmentVideoPlayerBinding by viewBinding(FragmentVideoPlayerBinding::bind)

    private val args: VideoPlayerFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            loadLesson.offer(LoadLesson(args.lessonId))
        }
    }

    private val loadLesson = ConflatedBroadcastChannel<LoadLesson>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.processIntent(intents)
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    override fun onPause() {
        super.onPause()
        binding.chapters.pausePlayer()
    }

    override fun onStop() {
        super.onStop()
        binding.chapters.stopPlayer()
    }

    override fun render(state: VideoViewState) {
        binding.chapters.render(state)
    }

    override val intents: Flow<VideoViewIntent>
        get() = merge(loadLesson.asFlow(), binding.chapters.intents)

}