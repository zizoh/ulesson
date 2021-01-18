package com.zizoh.ulesson.cache.impl

import com.zizoh.ulesson.cache.mappers.WatchedTopicCacheMapper
import com.zizoh.ulesson.cache.models.WatchedTopicCacheModel
import com.zizoh.ulesson.cache.room.WatchedTopicDao
import com.zizoh.ulesson.data.contract.cache.SubjectCache
import com.zizoh.ulesson.data.models.SubjectEntity
import com.zizoh.ulesson.data.models.WatchedTopicEntity
import javax.inject.Inject

/**
 * Created by zizoh on 17/January/2021.
 */

class SubjectCacheImpl @Inject constructor(
    private val dao: WatchedTopicDao,
    private val mapper: WatchedTopicCacheMapper
) : SubjectCache {

    override suspend fun getSubjects(): List<SubjectEntity> {
        return emptyList()
    }

    override suspend fun saveWatchedTopic(topic: WatchedTopicEntity) {
        val topicCache: WatchedTopicCacheModel = mapper.mapToModel(topic)
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