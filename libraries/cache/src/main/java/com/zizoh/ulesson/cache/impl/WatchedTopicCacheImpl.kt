package com.zizoh.ulesson.cache.impl

import com.zizoh.ulesson.cache.mappers.WatchedTopicCacheMapper
import com.zizoh.ulesson.cache.models.WatchedTopicCacheModel
import com.zizoh.ulesson.cache.room.dao.WatchedTopicDao
import com.zizoh.ulesson.data.contract.cache.WatchedTopicCache
import com.zizoh.ulesson.data.models.WatchedTopicEntity
import javax.inject.Inject

/**
 * Created by zizoh on 17/January/2021.
 */

class WatchedTopicCacheImpl @Inject constructor(
    private val dao: WatchedTopicDao,
    private val mapper: WatchedTopicCacheMapper
) : WatchedTopicCache {

    override suspend fun saveWatchedTopic(topic: WatchedTopicEntity) {
        val topicCache: WatchedTopicCacheModel = with(topic) {
            WatchedTopicCacheModel(id, name, icon, subjectName, subjectId, watchedDate)
        }
        dao.saveWatchedTopic(topicCache)
    }

    override suspend fun getMostRecentWatchedTopics(): List<WatchedTopicEntity> {
        val topics: List<WatchedTopicCacheModel> = dao.getMostRecentWatchedTopics()
        return mapper.mapToEntityList(topics)
    }

    override suspend fun getAllWatchedTopics(): List<WatchedTopicEntity> {
        val topics: List<WatchedTopicCacheModel> = dao.getAllRecentWatchedTopics()
        return mapper.mapToEntityList(topics)
    }
}