package com.github.evabishchevich.dynamicstyling.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.github.evabishchevich.dynamicstyling.R
import com.github.evabishchevich.dynamicstyling.ThemeManager

class CustomImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatImageView(context, attrs, defStyleAttr), ThemeManager.ThemeChangedListener {

    private var attrVal: String? = null

    init {
        if (attrs != null) {
            applyAttributes(context, attrs)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        ThemeManager.addListener(this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        ThemeManager.addListener(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        ThemeManager.removeListener(this)
    }

    override fun onThemeChanged(theme: ThemeManager.Theme) {
        setBackgroundColor(
            ContextCompat.getColor(context, theme.viewGroupTheme.backgroundColor)
        )
        updateDrawable(ContextThemeWrapper(context, theme.style))
    }

    private fun applyAttributes(context: Context, attrs: AttributeSet) {
        attrVal = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "custom_icon")

        updateDrawable(context)
    }

    private fun updateDrawable(ctx: Context) {
        val attributeValue = attrVal
        if (attributeValue != null && attributeValue.isEmpty().not()) {
            val resId = getResId(ctx, attributeValue)
            setImageDrawable(ContextCompat.getDrawable(ctx, resId))
            printSomeData()
        }
    }

    private fun printSomeData() {
        Log.d("XXX", "===== Icons data ===== ")
        Log.d("XXX", "Coffee day: ${R.drawable.ic_coffee_day}")
        Log.d("XXX", "Coffee night: ${R.drawable.ic_coffee_night}")
        Log.d("XXX", "Coffee selector: ${R.drawable.ic_coffee_selector}")
        Log.d("XXX", "Coffee selected: ${R.drawable.ic_coffee_selected}")
        Log.d("XXX", "Coffee icon attr: ${R.attr.coffee_icon}")
        Log.d("XXX", "Custom icon attr: ${R.attr.custom_icon}")
    }
}
