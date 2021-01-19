package com.zizoh.ulesson.dashboard.navigation

interface NavigationDispatcher {

    fun openSubjectFragment(subjectId: Int)

    fun openVideoFragmentFromDashboardFragment(lessonId: Int)

    fun openVideoFragmentFromChapterFragment(lessonId: Int)

    fun goBack()
}
