package com.zizoh.ulesson.dashboard.ui.chapter.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zizoh.ulesson.core.ext.inflate
import com.zizoh.ulesson.dashboard.R
import com.zizoh.ulesson.dashboard.databinding.ItemChapterBinding
import com.zizoh.ulesson.dashboard.presentation.models.ChapterModel
import javax.inject.Inject

/**
 * Created by zizoh on 19/January/2021.
 */

typealias LessonClickListener = (Int) -> Unit

class ChapterAdapter @Inject constructor(
) : ListAdapter<ChapterModel, ChapterAdapter.ChapterViewHolder>(diffUtilCallback) {

    @Inject
    lateinit var lessonAdapter: LessonAdapter

    var clickListener: LessonClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        return ChapterViewHolder(
            ItemChapterBinding.bind(parent.inflate(R.layout.item_chapter)),
            lessonAdapter
        )
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ChapterViewHolder(
        private val binding: ItemChapterBinding,
        private val lessonAdapter: LessonAdapter
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(chapter: ChapterModel, clickListener: LessonClickListener?) {
            with(binding) {
                tvChapterName.text = chapter.name
                lessonAdapter.clickListener = clickListener
                rvLessons.adapter = lessonAdapter.apply {
                    submitList(chapter.lessons)
                }
            }
        }
    }

    companion object {
        val diffUtilCallback: DiffUtil.ItemCallback<ChapterModel>
            get() = object : DiffUtil.ItemCallback<ChapterModel>() {
                override fun areItemsTheSame(
                    oldItem: ChapterModel,
                    newItem: ChapterModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: ChapterModel,
                    newItem: ChapterModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}