package com.zizoh.ulesson.domain.models

/**
 * Created by zizoh on 15/January/2021.
 */

data class Subject(
    val id: Int,
    val name: String,
    val icon: String,
    val chapters: List<Chapter>
)