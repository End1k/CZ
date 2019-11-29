package com.example.myapplication.controller

import android.graphics.Point
import android.util.Log
import com.example.myapplication.Game

class PlayerVsAiController(gn: Game) : Controller(gn) {
    override fun turni(p: Point) {
        if (g.pole[p.x][p.y]==0 && !g.check()) {
            if (checkTurn()) {
                setPlace(p, getC())
                g.turn++
                turni(getPlayer()!!.turning(g))
            }
            else {
                setPlace(p, getC())
                g.turn++
            }
        }
    }

}