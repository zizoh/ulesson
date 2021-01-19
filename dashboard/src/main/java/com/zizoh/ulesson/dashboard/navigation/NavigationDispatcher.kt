package com.zizoh.ulesson.dashboard.navigation

interface NavigationDispatcher {

    fun openSubjectFragment(subjectId: Int)

    fun openVideoFragment(lessonId: Int)

    fun goBack()
}
