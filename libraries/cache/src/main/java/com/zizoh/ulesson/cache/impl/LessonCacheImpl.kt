package com.zizoh.ulesson.cache.impl

import com.zizoh.ulesson.cache.mappers.LessonCacheModelMapper
import com.zizoh.ulesson.cache.models.LessonCacheModel
import com.zizoh.ulesson.cache.room.dao.LessonDao
import com.zizoh.ulesson.data.contract.cache.LessonCache
import com.zizoh.ulesson.data.models.LessonEntity
import javax.inject.Inject

/**
 * Created by zizoh on 19/January/2021.
 */

class LessonCacheImpl @Inject constructor(
    private val dao: LessonDao,
    private val mapper: LessonCacheModelMapper
) : LessonCache {

    override suspend fun saveLessons(lessons: List<LessonEntity>) {
        val lessonsCache: List<LessonCacheModel> = mapper.mapToModelList(lessons)
        dao.saveLessons(lessonsCache)
    }

    override suspend fun getLesson(id: Int): LessonEntity {
        val lessonCache: LessonCacheModel = dao.getLessonWithId(id)
        return mapper.mapToEntity(lessonCache)
    }
}