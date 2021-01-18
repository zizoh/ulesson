package com.zizoh.ulesson.dashboard.ui.dashboard.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zizoh.ulesson.core.ext.inflate
import com.zizoh.ulesson.dashboard.R
import com.zizoh.ulesson.dashboard.databinding.ItemLessonBinding
import com.zizoh.ulesson.dashboard.presentation.models.WatchedTopicModel
import com.zizoh.ulesson.dashboard.ui.dashboard.adapter.resourceprovider.recenttopic.RecentTopicResourceProviderFactory
import javax.inject.Inject

/**
 * Created by zizoh on 16/January/2021.
 */

typealias RecentTopicClickListener = (Int) -> Unit

class RecentTopicAdapter @Inject constructor(
) : ListAdapter<WatchedTopicModel, RecentTopicAdapter.RecentTopicViewHolder>(diffUtilCallback) {

    var clickListener: RecentTopicClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentTopicViewHolder {
        return RecentTopicViewHolder(ItemLessonBinding.bind(parent.inflate(R.layout.item_lesson)))
    }

    override fun onBindViewHolder(holder: RecentTopicViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class RecentTopicViewHolder(
        private val binding: ItemLessonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: WatchedTopicModel, clickListener: RecentTopicClickListener?) {
            val resourceFactory = RecentTopicResourceProviderFactory(lesson, binding.root.context)
            val provider = resourceFactory.getProvider()
            binding.ivPlayButton.setImageDrawable(provider.getPlayButtonDrawable())
            binding.tvItemLessonSubjectName.text = lesson.subjectName
            binding.tvItemLessonName.text = lesson.name
            binding.root.setOnClickListener {
                clickListener?.invoke(lesson.id)
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