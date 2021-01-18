package com.zizoh.ulesson.data.contract.cache

import com.zizoh.ulesson.data.models.SubjectEntity
import com.zizoh.ulesson.data.models.WatchedTopicEntity

/**
 * Created by zizoh on 17/January/2021.
 */

interface SubjectCache {

    suspend fun getSubjects(): List<SubjectEntity>

    suspend fun saveWatchedTopic(topic: WatchedTopicEntity)

    suspend fun getMostRecentWatchedTopics(): List<WatchedTopicEntity>

    suspend fun getAllWatchedTopics(): List<WatchedTopicEntity>
}