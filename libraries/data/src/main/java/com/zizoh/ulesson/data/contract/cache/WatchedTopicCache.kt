package com.zizoh.ulesson.data.contract.cache

import com.zizoh.ulesson.data.models.WatchedTopicEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by zizoh on 17/January/2021.
 */

interface WatchedTopicCache {

    suspend fun saveWatchedTopic(topic: WatchedTopicEntity)

    fun getMostRecentWatchedTopics(): Flow<List<WatchedTopicEntity>>

    fun getAllWatchedTopics(): Flow<List<WatchedTopicEntity>>
}