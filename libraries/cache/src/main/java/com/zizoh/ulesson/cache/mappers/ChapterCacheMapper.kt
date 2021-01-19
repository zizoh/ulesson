package com.zizoh.ulesson.cache.mappers

import com.zizoh.ulesson.cache.mappers.base.CacheModelMapper
import com.zizoh.ulesson.cache.models.ChapterCacheModel
import com.zizoh.ulesson.data.models.ChapterEntity
import javax.inject.Inject

/**
 * Created by zizoh on 18/January/2021.
 */

class ChapterCacheMapper @Inject constructor() :
    CacheModelMapper<ChapterCacheModel, ChapterEntity> {

    override fun mapToEntity(model: ChapterCacheModel): ChapterEntity {
        return with(model) {
            ChapterEntity(chapterId, name, emptyList(), subjectId, subjectName)
        }
    }

    override fun mapToModel(entity: ChapterEntity): ChapterCacheModel {
        return with(entity) {
            ChapterCacheModel(id, name, subjectId, subjectName)
        }
    }
}