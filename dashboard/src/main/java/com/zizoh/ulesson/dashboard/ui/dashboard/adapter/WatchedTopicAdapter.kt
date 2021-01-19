package com.zizoh.ulesson.dashboard.ui.dashboard.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zizoh.ulesson.core.ext.inflate
import com.zizoh.ulesson.dashboard.R
import com.zizoh.ulesson.dashboard.databinding.ItemWatchedTopicBinding
import com.zizoh.ulesson.dashboard.presentation.models.WatchedTopicModel
import com.zizoh.ulesson.dashboard.ui.dashboard.adapter.resourceprovider.recenttopic.RecentTopicResourceProviderFactory
import com.zizoh.ulesson.dashboard.views.ImageLoader
import javax.inject.Inject

/**
 * Created by zizoh on 16/January/2021.
 */

typealias WatchedTopicClickListener = (Int) -> Unit

class WatchedTopicAdapter @Inject constructor(
) : ListAdapter<WatchedTopicModel, WatchedTopicAdapter.RecentTopicViewHolder>(diffUtilCallback) {

    @Inject
    lateinit var imageLoader: ImageLoader

    var clickListener: WatchedTopicClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentTopicViewHolder {
        return RecentTopicViewHolder(
            ItemWatchedTopicBinding.bind(parent.inflate(R.layout.item_watched_topic)),
            imageLoader
        )
    }

    override fun onBindViewHolder(holder: RecentTopicViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class RecentTopicViewHolder(
        private val binding: ItemWatchedTopicBinding,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: WatchedTopicModel, clickListener: WatchedTopicClickListener?) {
            val resourceFactory = RecentTopicResourceProviderFactory(lesson, binding.root.context)
            val provider = resourceFactory.getProvider()
            with(binding) {
                ivPlayButton.setImageDrawable(provider.getPlayButtonDrawable())
                tvItemLessonSubjectName.text = lesson.subjectName
                tvItemTopicName.text = lesson.name
                root.setOnClickListener {
                    clickListener?.invoke(lesson.id)
                }
                imageLoader.loadImage(lesson.icon, ivLessonImage, 16)
            }

        }
    }

    companion object {
        val diffUtilCallback: DiffUtil.ItemCallback<WatchedTopicModel>
            get() = object : DiffUtil.ItemCallback<WatchedTopicModel>() {
                override fun areItemsTheSame(
                    oldItem: WatchedTopicModel,
                    newItem: WatchedTopicModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: WatchedTopicModel,
                    newItem: WatchedTopicModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}