package com.example.myapplication.mode

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.example.myapplication.Game
import com.example.myapplication.R
import com.example.myapplication.player.AiTicTacToe
import com.example.myapplication.player.RealPlayer
import kotlinx.android.synthetic.main.activity_main.*

class vsRealPlayerActivity : AppCompatActivity() {

    var ai1 : AiTicTacToe =
        AiTicTacToe()
    var playe : RealPlayer =
        RealPlayer()
    var g : Game = Game(ai1, playe)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_real_player)

        ai1.pTurn = 0
        playe.pTurn = 1

        viewttt.g = g

        viewttt.setOnTouchListener(
            object : View.OnTouchListener {
                override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                    var p : Point = Point()
                    if (p1 != null) {
                        p.x = (p1.y / (viewttt.height / g.psize)).toInt()
                        p.y = (p1.x / (viewttt.width / g.psize)).toInt()

                        g.roundUser(p)
                    }
                    return true
                }
        })

        g.round()
    }
    fun bclick(v: View)
    {
        g.psize = 10
        g.needToWin = 5
        g.pole = Array(g.psize, { Array(g.psize, {0})})
        g.turn = 0
    }

}