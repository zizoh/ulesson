package com.zizoh.ulesson.cache.mappers

import com.zizoh.ulesson.cache.mappers.base.CacheModelMapper
import com.zizoh.ulesson.cache.models.WatchedTopicCacheModel
import com.zizoh.ulesson.data.models.WatchedTopicEntity
import javax.inject.Inject

/**
 * Created by zizoh on 17/January/2021.
 */

class WatchedTopicCacheMapper @Inject constructor() :
    CacheModelMapper<WatchedTopicCacheModel, WatchedTopicEntity> {

    override fun mapToEntity(model: WatchedTopicCacheModel): WatchedTopicEntity {
        return with(model) {
            WatchedTopicEntity(
                id,
                name,
                icon,
                mediaUrl,
                subjectId,
                subjectName,
                chapterId,
                watchedDate
            )
        }
    }

    override fun mapToModel(entity: WatchedTopicEntity): WatchedTopicCacheModel {
        return with(entity) {
            WatchedTopicCacheModel(
                id,
                name,
                icon,
                mediaUrl,
                subjectId,
                subjectName,
                chapterId,
                watchedDate
            )
        }
    }
}