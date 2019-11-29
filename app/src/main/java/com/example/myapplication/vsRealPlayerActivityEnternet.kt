package com.example.myapplication

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.myapplication.Game
import com.example.myapplication.R
import com.example.myapplication.controller.NetworkController
import com.example.myapplication.controller.PlayerVsAiController
import com.example.myapplication.controller.PlayerVsPlayerController
import com.example.myapplication.player.AiTicTacToe
import com.example.myapplication.player.RealPlayer
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_vs_real_player_enternet.*

class vsRealPlayerActivityEnternet : AppCompatActivity() {


    var playe1: RealPlayer = RealPlayer()
    var playe2: RealPlayer = RealPlayer()

    var g: Game = Game(playe1, playe2)

    var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    var myRef: DatabaseReference = firebaseDatabase.getReference("points")
    var sd: NetworkController = NetworkController(g, myRef)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_real_player_enternet)

        playe1.pTurn = 0
        playe2.pTurn = 1

        viewtt.g = g

        viewtt.setOnTouchListener(

            object : View.OnTouchListener {
                override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {

                    var p: Point = Point()

                    if (p1 != null) {
                        p.x = (p1.y / (viewtt.height / g.psize)).toInt()
                        p.y = (p1.x / (viewtt.width / g.psize)).toInt()



                        if (g.pole[p.x][p.y] == 0) {       //&& g.turn == 0
                            Log.d("TEST",g.turn.toString())
                            g.pole[p.x][p.y] = sd.getC()
                            g.turn++
                            myRef.push().setValue(Point(p))

                        }

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
        myRef.removeValue()
    }





}
