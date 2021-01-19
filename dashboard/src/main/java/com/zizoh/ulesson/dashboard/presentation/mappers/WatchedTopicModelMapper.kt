package com.zizoh.ulesson.dashboard.presentation.mappers

import com.zizoh.ulesson.dashboard.presentation.models.WatchedTopicModel
import com.zizoh.ulesson.domain.models.WatchedTopic
import com.zizoh.ulesson.presentation.mapper.ModelMapper
import javax.inject.Inject

/**
 * Created by zizoh on 18/January/2021.
 */

class WatchedTopicModelMapper @Inject constructor() : ModelMapper<WatchedTopicModel, WatchedTopic> {

    override fun mapToModel(domain: WatchedTopic): WatchedTopicModel {
        return with(domain) {
            WatchedTopicModel(id, name, icon, watchedDate, subjectName)
        }
    }

    override fun mapToDomain(model: WatchedTopicModel): WatchedTopic {
        return with(model) {
            WatchedTopic(id, name, icon, watchedDate, subjectName)
        }
    }
}