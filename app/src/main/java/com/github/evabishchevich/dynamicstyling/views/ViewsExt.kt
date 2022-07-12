package com.github.evabishchevich.dynamicstyling.views

import android.content.Context
import android.util.TypedValue

internal fun getResId(context: Context, attribute: String): Int {
    return when (attribute[0]) {
        '?' -> getResIdFromAttribute(context, attribute)
        '@' -> getIdFromAttribute(attribute)
        else -> throw IllegalArgumentException("Unknown type of resource")
    }
}

private fun getResIdFromAttribute(ctx: Context, attribute: String): Int {
    val attrId = getIdFromAttribute(attribute)
    val typedValue = TypedValue()
    ctx.theme.resolveAttribute(attrId, typedValue, true)
    return typedValue.resourceId
}

private fun getIdFromAttribute(attribute: String): Int {
    return attribute.substring(1, attribute.length).toInt()
}
