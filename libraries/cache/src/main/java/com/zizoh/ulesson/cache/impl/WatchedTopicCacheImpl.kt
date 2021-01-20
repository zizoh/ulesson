package com.zizoh.ulesson.cache.impl

import com.zizoh.ulesson.cache.mappers.WatchedTopicCacheMapper
import com.zizoh.ulesson.cache.models.WatchedTopicCacheModel
import com.zizoh.ulesson.cache.room.dao.WatchedTopicDao
import com.zizoh.ulesson.data.contract.cache.WatchedTopicCache
import com.zizoh.ulesson.data.models.WatchedTopicEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override fun getMostRecentWatchedTopics(): Flow<List<WatchedTopicEntity>> {
        return dao.getMostRecentWatchedTopics().map {
            mapper.mapToEntityList(it)
        }
    }

    override fun getAllWatchedTopics(): Flow<List<WatchedTopicEntity>> {
        return dao.getAllRecentWatchedTopics().map {
            mapper.mapToEntityList(it)
        }
    }
}