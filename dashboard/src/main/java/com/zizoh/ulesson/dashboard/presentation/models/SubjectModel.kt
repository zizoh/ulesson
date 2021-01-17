package com.zizoh.ulesson.dashboard.presentation.models

/**
 * Created by zizoh on 15/January/2021.
 */

data class SubjectModel(
    val id: Int,
    val name: String,
    val icon: String,
    val chapters: List<ChapterModel>
)