package com.example.myapplication.player

import android.graphics.Point
import com.example.myapplication.Game

open class Player() {
    var pTurn = -1

    open fun turning(g: Game) : Point
    {
        return Point()
    }

}