package com.zizoh.ulesson.dashboard.presentation.models

/**
 * Created by zizoh on 18/January/2021.
 */

data class WatchedTopicModel(
    val id: Int,
    val name: String,
    val icon: String,
    val mediaUrl: String,
    val subjectId: Int,
    val subjectName: String,
    val chapterId: Int,
    val watchedDate: Long
)