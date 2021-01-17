package com.zizoh.ulesson.dashboard.ui.dashboard.adapter.resourceprovider.recenttopic

import android.content.Context
import com.zizoh.ulesson.dashboard.presentation.models.LessonModel
import com.zizoh.ulesson.dashboard.ui.dashboard.adapter.resourceprovider.exception.NoResourceException
import com.zizoh.ulesson.dashboard.ui.dashboard.adapter.resourceprovider.subject.SubjectName

/**
 * Created by zizoh on 16/January/2021.
 */

class RecentTopicResourceProviderFactory(
    private val lesson: LessonModel,
    private val context: Context
) {
    fun getProvider(): RecentTopicResourceProvider {
        return when (lesson.subjectName) {
            SubjectName.BIOLOGY -> BiologyLessonResourceProvider(context)
            SubjectName.CHEMISTRY -> ChemistryLessonResourceProvider(context)
            SubjectName.ENGLISH -> EnglishLessonResourceProvider(context)
            SubjectName.MATHEMATICS -> MathematicsLessonResourceProvider(context)
            SubjectName.PHYSICS -> PhysicsLessonResourceProvider(context)
            else -> throw NoResourceException("No resources for ${lesson.subjectName}.")
        }
    }
}