package com.zizoh.ulesson.dashboard.views.video

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.zizoh.ulesson.dashboard.databinding.LayoutVideoBinding
import com.zizoh.ulesson.dashboard.navigation.NavigationDispatcher
import com.zizoh.ulesson.dashboard.presentation.video.mvi.VideoViewIntent
import com.zizoh.ulesson.dashboard.presentation.video.mvi.VideoViewState
import com.zizoh.ulesson.presentation.mvi.MVIView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by zizoh on 19/January/2021.
 */

@AndroidEntryPoint
class VideoView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet),
    MVIView<VideoViewIntent, VideoViewState> {

    @Inject
    lateinit var navigator: Provider<NavigationDispatcher>

    private var binding: LayoutVideoBinding

    init {
        isSaveEnabled = true
        val inflater: LayoutInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        binding = LayoutVideoBinding.inflate(inflater, this, true)
        binding.ivBackButton.setOnClickListener {
            navigator.get().goBack()
        }
    }

    override fun render(state: VideoViewState) {
        when (state) {
            VideoViewState.Idle -> {

            }
            VideoViewState.Loading -> {
            }
            is VideoViewState.LessonLoaded -> {
                with(binding) {
                    tvVideoLessonName.text = state.lesson.name
                    tvVideoChapterName.text = state.lesson.chapterName
                }
            }
            is VideoViewState.Error -> {

            }
        }
    }

    override val intents: Flow<VideoViewIntent>
        get() = flowOf()
}
