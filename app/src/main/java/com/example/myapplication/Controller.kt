package com.example.myapplication

import android.graphics.Point
import com.example.myapplication.player.Player

class Controller(gn: Game?) {
    var g: Game? = gn
    var p1 : Player? = null
    var p2 : Player? = null

    var turn : Int = 1


    fun checkTurn(): Boolean{
        return (turn%2 == g!!.thisPlayer)
    }
    fun getPlayer(): Player?
    {
        if (turn%2 == 1) return p1
        if (turn%2 == 0) return p2
        return Player()
    }
    fun getC(): Int
    {
        if (turn%2 == 1) return 1
        if (turn%2 == 0) return 2
        return 0
    }


    fun setPlace(p :Point, char : Int){
        g!!.pole[p.x][p.y] = char
    }

    fun turn(p: Point) {
        if (g!!.pole[p.x][p.y]==0) {
            if (checkTurn()) {
                setPlace(getPlayer()!!.turning(g!!), getC())
                turn++
                turn(getPlayer()!!.turning(g!!))
            }
        }
        else {
            setPlace(getPlayer()!!.turning(g!!), getC())
            turn++
        }

    }

}