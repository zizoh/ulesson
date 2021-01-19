package com.zizoh.ulesson.dashboard.ui.dashboard.adapter.resourceprovider.subject

import android.content.Context
import com.zizoh.ulesson.dashboard.presentation.models.SubjectModel
import com.zizoh.ulesson.dashboard.ui.dashboard.adapter.resourceprovider.exception.NoResourceException

/**
 * Created by zizoh on 16/January/2021.
 */

class SubjectResourceProviderFactory(
    private val subject: SubjectModel,
    private val context: Context
) {
    fun getProvider(): SubjectResourceProvider {
        return when (subject.name) {
            SubjectName.BIOLOGY -> BiologyResourceProvider(context)
            SubjectName.CHEMISTRY -> ChemistryResourceProvider(context)
            SubjectName.ENGLISH -> EnglishResourceProvider(context)
            SubjectName.MATHEMATICS -> MathematicsResourceProvider(context)
            SubjectName.PHYSICS -> PhysicsResourceProvider(context)
            else -> throw NoResourceException("No resources for ${subject.name}.")
        }
    }
}