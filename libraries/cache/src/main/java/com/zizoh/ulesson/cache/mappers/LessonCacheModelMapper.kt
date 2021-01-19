package com.zizoh.ulesson.cache.mappers

import com.zizoh.ulesson.cache.mappers.base.CacheModelMapper
import com.zizoh.ulesson.cache.models.LessonCacheModel
import com.zizoh.ulesson.data.models.LessonEntity
import javax.inject.Inject

/**
 * Created by zizoh on 18/January/2021.
 */

class LessonCacheModelMapper @Inject constructor() :
    CacheModelMapper<LessonCacheModel, LessonEntity> {

    override fun mapToEntity(model: LessonCacheModel): LessonEntity {
        return with(model) {
            LessonEntity(id, name, icon, mediaUrl, subjectId, chapterId)
        }
    }

    override fun mapToModel(entity: LessonEntity): LessonCacheModel {
        return with(entity) {
            LessonCacheModel(id, name, icon, mediaUrl, subjectId, chapterId)
        }
    }
}