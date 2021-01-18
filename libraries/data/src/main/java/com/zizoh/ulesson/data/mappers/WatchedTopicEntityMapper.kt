package com.zizoh.ulesson.data.mappers

import com.zizoh.ulesson.data.mappers.base.EntityMapper
import com.zizoh.ulesson.data.models.WatchedTopicEntity
import com.zizoh.ulesson.domain.models.WatchedTopic
import javax.inject.Inject

/**
 * Created by zizoh on 17/January/2021.
 */

class WatchedTopicEntityMapper @Inject constructor() :
    EntityMapper<WatchedTopicEntity, WatchedTopic> {
    override fun mapFromEntity(entity: WatchedTopicEntity): WatchedTopic {
        return with(entity) {
            WatchedTopic(
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

    override fun mapToEntity(domain: WatchedTopic): WatchedTopicEntity {
        return with(domain) {
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
}