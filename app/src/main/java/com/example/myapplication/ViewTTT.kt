package com.example.myapplication

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class ViewTTT(context: Context, attributeSet: AttributeSet) : View(context, attributeSet){
    var g: Game? = null

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        g?.draw(canvas)
        invalidate()
    }
}