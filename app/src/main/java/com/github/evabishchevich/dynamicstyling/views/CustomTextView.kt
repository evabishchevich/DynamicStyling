package com.github.evabishchevich.dynamicstyling.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.github.evabishchevich.dynamicstyling.ThemeManager

class CustomTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr), ThemeManager.ThemeChangedListener {

    private var textColorAttrValue: String? = null

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
        updateTextColor(ContextThemeWrapper(context, theme.style))
    }

    private fun applyAttributes(context: Context, attrs: AttributeSet) {
        textColorAttrValue = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "textColor")

        updateTextColor(context)
    }

    private fun updateTextColor(ctx: Context) {
        val attributeValue = textColorAttrValue
        if (attributeValue != null && attributeValue.isEmpty().not()) {
            val resId = getResId(ctx, attributeValue)
            setTextColor(ContextCompat.getColor(ctx, resId))
        }
    }
}
