package com.taurus.modernandroiddevelopmentkata.core.utilities

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.*
import androidx.core.content.ContextCompat
import javax.inject.Inject

class ResourceHelper @Inject constructor(private val context: Context) {

    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, formatArgs)
    }

    fun getInteger(@IntegerRes resId: Int): Int {
        return context.getResources().getInteger(resId)
    }

    fun getDrawable(@DrawableRes resId: Int): Drawable? {

        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            context.resources.getDrawable(resId, context.theme)
        } else ContextCompat.getDrawable(context, resId)

    }

    @ColorInt
    fun getColor(@ColorRes resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }

    fun getDimension(@DimenRes resId: Int): Float {
        return context.getResources().getDimension(resId)
    }

    @Px
    fun getDimensionPixelSize(@DimenRes resId: Int): Int {
        return context.getResources().getDimensionPixelSize(resId)
    }
}

