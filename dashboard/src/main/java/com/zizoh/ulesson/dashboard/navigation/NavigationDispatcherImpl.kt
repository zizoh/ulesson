package com.zizoh.ulesson.dashboard.navigation

import androidx.navigation.NavController
import javax.inject.Inject

class NavigationDispatcherImpl @Inject constructor(
    private val navController: NavController
) : NavigationDispatcher {

    override fun openSubjectFragment(subjectId: Int) {
    }

    override fun openVideoFragment(lessonId: Int) {
    }

    override fun goBack() {
        navController.navigateUp()
    }

    companion object {
        const val SUBJECT_ID_ARG: String = "subject_id"
        const val LESSON_ID_ARG: String = "lesson_id"
    }
}