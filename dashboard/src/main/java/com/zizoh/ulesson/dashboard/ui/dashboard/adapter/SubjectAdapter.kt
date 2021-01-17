package com.zizoh.ulesson.dashboard.ui.dashboard.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zizoh.ulesson.core.ext.inflate
import com.zizoh.ulesson.dashboard.R
import com.zizoh.ulesson.dashboard.databinding.ItemSubjectBinding
import com.zizoh.ulesson.dashboard.presentation.models.SubjectModel
import com.zizoh.ulesson.dashboard.ui.dashboard.adapter.resourceprovider.subject.SubjectResourceProviderFactory
import javax.inject.Inject

/**
 * Created by zizoh on 16/January/2021.
 */

typealias SubjectClickListener = (Int) -> Unit

class SubjectAdapter @Inject constructor(
) : ListAdapter<SubjectModel, SubjectAdapter.SubjectViewHolder>(diffUtilCallback) {

    var clickListener: SubjectClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        return SubjectViewHolder(ItemSubjectBinding.bind(parent.inflate(R.layout.item_subject)))
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class SubjectViewHolder(
        private val binding: ItemSubjectBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(subject: SubjectModel, clickListener: SubjectClickListener?) {
            val resourceFactory = SubjectResourceProviderFactory(subject, binding.root.context)
            val provider = resourceFactory.getProvider()
            binding.ivSubjectIcon.setImageDrawable(provider.getBackgroundDrawable())
            binding.root.setOnClickListener {
                clickListener?.invoke(subject.id)
            }
        }
    }

    companion object {
        val diffUtilCallback: DiffUtil.ItemCallback<SubjectModel>
            get() = object : DiffUtil.ItemCallback<SubjectModel>() {
                override fun areItemsTheSame(
                    oldItem: SubjectModel,
                    newItem: SubjectModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: SubjectModel,
                    newItem: SubjectModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}