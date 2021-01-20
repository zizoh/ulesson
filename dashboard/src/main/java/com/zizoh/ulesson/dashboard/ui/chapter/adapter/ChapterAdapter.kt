package com.zizoh.ulesson.dashboard.ui.chapter.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zizoh.ulesson.core.ext.inflate
import com.zizoh.ulesson.core.ext.safeOffer
import com.zizoh.ulesson.dashboard.R
import com.zizoh.ulesson.dashboard.databinding.ItemChapterBinding
import com.zizoh.ulesson.dashboard.presentation.models.ChapterModel
import com.zizoh.ulesson.dashboard.presentation.models.LessonModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import javax.inject.Inject

/**
 * Created by zizoh on 19/January/2021.
 */

typealias LessonClickListener = (LessonModel) -> Unit

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

    val clicks: Flow<LessonModel>
        get() = callbackFlow {
            val listener: LessonClickListener = { lesson: LessonModel ->
                safeOffer(lesson)
                Unit
            }
            clickListener = listener
            awaitClose { clickListener = null }
        }.conflate()

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