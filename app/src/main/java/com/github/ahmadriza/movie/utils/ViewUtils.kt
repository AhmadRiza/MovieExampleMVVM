package com.github.ahmadriza.movie.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ahmadriza.movie.utils.android.CurrencyTextWatcher
import com.github.ahmadriza.movie.utils.android.DelayTextWatcher
import com.github.ahmadriza.movie.utils.android.InfiniteScrollListener

/**
 * Created on 11/28/20.
 */

fun Context.getCompatColor(@ColorRes color: Int) = ContextCompat.getColor(this, color)

fun View.disable() {
    isEnabled = false
}

fun View.enable() {
    isEnabled = true
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visibleOrGone(isVisible: Boolean) {
    if (isVisible) visible() else gone()
}

fun String.isAnValidEmail(): Boolean {
    return if (isNullOrBlank()) {
        false
    } else {
        android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
}

fun EditText.addOnSubmitListener(delay: Long, onSubmit: (s: String) -> Unit) {
    addTextChangedListener(DelayTextWatcher(delay) {
         onSubmit.invoke(it)
    })
}

fun AppCompatTextView.setDrawableIcon(
    leftDrawable: Int = 0, topDrawable: Int = 0,
    rightDrawable: Int = 0, bottomDrawable: Int = 0
) {
    setCompoundDrawablesWithIntrinsicBounds(
        leftDrawable, topDrawable, rightDrawable, bottomDrawable
    )
}

fun AppCompatEditText.setDrawableIcon(
    leftDrawable: Int = 0, topDrawable: Int = 0,
    rightDrawable: Int = 0, bottomDrawable: Int = 0
) {
    setCompoundDrawablesWithIntrinsicBounds(
        leftDrawable, topDrawable, rightDrawable, bottomDrawable
    )
}


fun View.getYPosition(activity: Activity, rootView: View): Int{

    val dm = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(dm)
    val topOffset = dm.heightPixels - rootView.measuredHeight

    val loc = intArrayOf(0,0)
    getLocationOnScreen(loc)

    return loc[1] - topOffset

}

/*
https://proandroiddev.com/android-full-screen-ui-with-transparent-status-bar-ef52f3adde63
 */
fun Activity.makeStatusBarTransparent() {

    window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        statusBarColor = Color.TRANSPARENT
    }

}

fun View.setMarginTop(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(0, marginTop, 0, 0)
    this.layoutParams = menuLayoutParams
}

fun EditText.attachCurrencyFormatter() {
    addTextChangedListener(CurrencyTextWatcher(this))
}

fun RecyclerView.onScrollLoad(threshold: Int, action:()->Unit){
    val lm = (layoutManager as LinearLayoutManager?)?: return
    addOnScrollListener(object : InfiniteScrollListener(lm, threshold){
        override fun loadMore() {
            action.invoke()
        }

    })
}


fun TextView.setMaxLinesForEllipsizing() = doOnPreDraw {
    val numberOfCompletelyVisibleLines = (measuredHeight - paddingTop - paddingBottom) / lineHeight
    maxLines = numberOfCompletelyVisibleLines
}