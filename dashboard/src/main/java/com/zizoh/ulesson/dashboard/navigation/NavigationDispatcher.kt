package com.zizoh.ulesson.dashboard.navigation

import com.zizoh.ulesson.dashboard.presentation.models.LessonModel

interface NavigationDispatcher {

    fun openSubjectFragment(subjectId: Int)

    fun openVideoFragmentFromDashboardFragment(lessonId: Int)

    fun openVideoFragmentFromChapterFragment(lesson: LessonModel)

    fun goBack()
}
