package com.github.evabishchevich.dynamicstyling

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.github.evabishchevich.dynamicstyling.views.CustomButton
import com.github.evabishchevich.dynamicstyling.views.CustomImageView
import com.github.evabishchevich.dynamicstyling.views.CustomLinearLayout
import com.github.evabishchevich.dynamicstyling.views.CustomTextView

class CustomViewsInflater(
    private val delegate: AppCompatDelegate
) : LayoutInflater.Factory2 {

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        return when (name) {
            "TextView" -> CustomTextView(context, attrs)
            "LinearLayout" -> CustomLinearLayout(context, attrs)
            "Button" -> CustomButton(context, attrs, androidx.appcompat.R.attr.buttonStyle)
            "ImageView" -> CustomImageView(context, attrs)
            else -> delegate.createView(parent, name, context, attrs)
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return onCreateView(null, name, context, attrs)
    }
}
