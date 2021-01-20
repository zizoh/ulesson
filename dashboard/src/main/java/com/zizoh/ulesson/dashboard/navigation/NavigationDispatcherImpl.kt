package com.zizoh.ulesson.dashboard.navigation

import androidx.navigation.NavController
import com.zizoh.ulesson.dashboard.presentation.models.LessonModel
import com.zizoh.ulesson.dashboard.ui.chapter.ChaptersFragmentDirections.Companion.actionChaptersFragmentToVideoPlayerFragment
import com.zizoh.ulesson.dashboard.ui.dashboard.DashboardFragmentDirections.Companion.actionDashboardFragmentToChaptersFragment
import com.zizoh.ulesson.dashboard.ui.dashboard.DashboardFragmentDirections.Companion.actionDashboardFragmentToVideoPlayerFragment
import javax.inject.Inject

class NavigationDispatcherImpl @Inject constructor(
    private val navController: NavController
) : NavigationDispatcher {

    override fun openSubjectFragment(subjectId: Int) {
        navController.navigate(actionDashboardFragmentToChaptersFragment(subjectId))
    }

    override fun openVideoFragmentFromDashboardFragment(lessonId: Int) {
        navController.navigate(actionDashboardFragmentToVideoPlayerFragment(lessonId))
    }

    override fun openVideoFragmentFromChapterFragment(lesson: LessonModel) {
        navController.navigate(actionChaptersFragmentToVideoPlayerFragment(lesson.id))
    }

    override fun goBack() {
        navController.navigateUp()
    }

}