package com.example.myapplication.controller

import android.graphics.Point
import com.example.myapplication.Game

class PlayerVsPlayerController(gn: Game) : Controller(gn) {
    override fun turni(p: Point) {
        if (g.pole[p.x][p.y]==0 && !g.check()) {
            setPlace(p, getC())
            g.turn++
        }
    }

}