package com.zizoh.ulesson.dashboard.ui.chapter.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zizoh.ulesson.core.ext.inflate
import com.zizoh.ulesson.dashboard.R
import com.zizoh.ulesson.dashboard.databinding.ItemLessonBinding
import com.zizoh.ulesson.dashboard.presentation.models.LessonModel
import com.zizoh.ulesson.dashboard.views.ImageLoader
import javax.inject.Inject

/**
 * Created by zizoh on 19/January/2021.
 */

class LessonAdapter @Inject constructor(
) : ListAdapter<LessonModel, LessonAdapter.LessonViewHolder>(diffUtilCallback) {

    @Inject
    lateinit var imageLoader: ImageLoader

    var clickListener: LessonClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(
            ItemLessonBinding.bind(parent.inflate(R.layout.item_lesson)),
            imageLoader
        )
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class LessonViewHolder(
        private val binding: ItemLessonBinding,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: LessonModel, clickListener: LessonClickListener?) {
            with(binding) {
                tvItemLessonName.text = lesson.name
                imageLoader.loadImage(lesson.icon, ivItemLessonImage, 0)
                root.setOnClickListener {
                    clickListener?.invoke(lesson)
                }
            }
        }
    }

    companion object {
        val diffUtilCallback: DiffUtil.ItemCallback<LessonModel>
            get() = object : DiffUtil.ItemCallback<LessonModel>() {
                override fun areItemsTheSame(
                    oldItem: LessonModel,
                    newItem: LessonModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: LessonModel,
                    newItem: LessonModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}