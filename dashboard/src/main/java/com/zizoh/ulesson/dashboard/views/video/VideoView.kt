package com.zizoh.ulesson.dashboard.views.video

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
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

    @Inject
    lateinit var simpleExoPlayer: SimpleExoPlayer

    @Inject
    lateinit var bandwidthMeter: DefaultBandwidthMeter

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
                    videoThumbnail.isVisible = false
                    playerView.isVisible = true
                    playVideo(state.lesson.mediaUrl)
                }
            }
            is VideoViewState.Error -> {

            }
        }
    }

    private fun LayoutVideoBinding.playVideo(mediaUrl: String) {
        playerView.player = simpleExoPlayer
        val mediaSource: MediaSource = provideMediaSource(mediaUrl)
        simpleExoPlayer.prepare(mediaSource, false, false)

        startPlayer()
    }

    private fun startPlayer() {
        simpleExoPlayer.playWhenReady = true
    }

    fun pausePlayer() {
        simpleExoPlayer.playWhenReady = false
    }

    fun stopPlayer() {
        simpleExoPlayer.stop()
    }

    private fun provideMediaSource(mediaUrl: String): MediaSource {
        val dataSourceFactory = DefaultHttpDataSourceFactory("ua", bandwidthMeter)
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(mediaUrl))
    }

    override val intents: Flow<VideoViewIntent>
        get() = flowOf()
}
