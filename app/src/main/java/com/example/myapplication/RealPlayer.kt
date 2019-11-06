package com.example.myapplication

import android.graphics.Point

class RealPlayer : Player(){
    var thisTurnChoice : Point = Point()

    override fun turning(g: Game) : Point
    {


        return Point()
    }

    fun getCoordsOfTTC(g : Game, p : Point)
    {
        if (g.turn%2 == pTurn)
        {
            thisTurnChoice = p
        }
    }
}