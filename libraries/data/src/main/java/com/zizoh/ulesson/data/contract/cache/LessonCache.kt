package com.zizoh.ulesson.data.contract.cache

import com.zizoh.ulesson.data.models.LessonEntity

/**
 * Created by zizoh on 19/January/2021.
 */

interface LessonCache {

    suspend fun saveLessons(lessons: List<LessonEntity>)

    suspend fun getLesson(id: Int): LessonEntity
}