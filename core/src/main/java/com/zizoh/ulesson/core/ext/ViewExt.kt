package com.zizoh.ulesson.core.ext

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.zizoh.ulesson.core.R
import kotlin.math.roundToInt

fun Context.getImage(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(this, id)
}

fun Context.getColorResId(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}

fun ViewGroup.inflate(layout: Int): View {
    val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    return layoutInflater.inflate(layout, this, false)
}

fun Fragment.showSnackbar(
    snackbarText: String,
    timeLength: Int,
    actionButtonText: String,
    action: View.OnClickListener?
) {
    activity?.let {
        val snack = Snackbar.make(it.findViewById(android.R.id.content), snackbarText, timeLength)
        snack.setAction(actionButtonText, action)
        snack.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        snack.setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.green))
        snack.setActionTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        val view = snack.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        view.layoutParams = params
        snack.show()
    }
}

fun Context.dpToPx(dp: Int): Int {
    val displayMetrics = resources.displayMetrics
    return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
}
