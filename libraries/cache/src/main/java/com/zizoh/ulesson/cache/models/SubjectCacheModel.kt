package com.zizoh.ulesson.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by zizoh on 15/January/2021.
 */

@Entity(tableName = "subjects")
data class SubjectCacheModel(
    @PrimaryKey
    val subjectId: Int,
    val name: String,
    val icon: String
)