package com.example.myapplication

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.*
import android.util.Log
import android.view.MotionEvent


class MainActivity : AppCompatActivity() {

    var ai1 : AiTicTacToe = AiTicTacToe()
    var g : Game = Game(ai1, ai1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewttt.g = g

        viewttt.setOnTouchListener(
            object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                var p : PointF = PointF()
                if (p1 != null) {
                    p.x = p1.y / (viewttt.height / g.psize)
                    p.y = p1.x / (viewttt.width / g.psize)
                    Log.d("POSITION", "${viewttt.width} , ${viewttt.height}")

                    g.roundUser(p) ///////////////////////////////////////////////////////////////////////

                    Log.d("POSITION", "$p")
                    Log.d("POSITION", "${p1.x} , ${p1.y}")
                }
                return true
                }

        })
    }
    fun bclick(v:View)
    {
        g.psize = 10
        g.needToWin = 5
        g.pole = Array(g.psize, { Array(g.psize, {0})})
        g.turn = 0
    }

}