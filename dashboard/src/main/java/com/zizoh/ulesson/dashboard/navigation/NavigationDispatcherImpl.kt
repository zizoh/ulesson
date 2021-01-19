package com.zizoh.ulesson.dashboard.navigation

import androidx.navigation.NavController
import com.zizoh.ulesson.dashboard.ui.dashboard.DashboardFragmentDirections
import javax.inject.Inject

class NavigationDispatcherImpl @Inject constructor(
    private val navController: NavController
) : NavigationDispatcher {

    override fun openSubjectFragment(subjectId: Int) {
        navController.navigate(
            DashboardFragmentDirections.actionDashboardFragmentToChaptersFragment(subjectId)
        )
    }

    override fun openVideoFragment(lessonId: Int) {
    }

    override fun goBack() {
        navController.navigateUp()
    }

}