package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.Dimension

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    lateinit var borderWidth: Int

    @Dimension getBorderWidth():Int {

    }

    setBorderWidth(@Dimension dp:Int) {

    }
}