package com.zizoh.ulesson.domain.models

/**
 * Created by zizoh on 17/January/2021.
 */

data class WatchedTopic(
    val id: Int,
    val name: String,
    val icon: String,
    val watchedDate: Long,
    val subjectName: String
)