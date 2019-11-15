package com.example.myapplication.controller

import android.graphics.Point
import com.example.myapplication.Game

class PlayerVsAiController(gn: Game) : Controller(gn) {
    override fun turni(p: Point) {
        if (g.pole[p.x][p.y]==0) {
            if (checkTurn()) {
                setPlace(p, getC())
                turn++
                turni(getPlayer()!!.turning(g))
            } else {
                setPlace(p, getC())
                turn++
            }
        }
    }

}