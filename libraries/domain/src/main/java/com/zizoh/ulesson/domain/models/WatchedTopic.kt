package com.zizoh.ulesson.domain.models

/**
 * Created by zizoh on 17/January/2021.
 */

data class WatchedTopic(
    val id: Int,
    val name: String,
    val icon: String,
    val mediaUrl: String,
    val subjectId: Int,
    val subjectName: String,
    val chapterId: Int,
    val watchedDate: Long
)