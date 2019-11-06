package com.example.myapplication

import android.graphics.Point

open class Player() {
    var pTurn = -1

    open fun turning(g: Game) : Point
    {
        return Point()
    }
}