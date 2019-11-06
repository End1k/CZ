package com.example.myapplication

import android.graphics.Point
import kotlin.math.pow

class AiTicTacToe : Player(){
    override fun turning(g: Game) : Point
    {
        return evaluation(g)
    }

    fun evaluation( g : Game): Point { // "il" - возвращает координаты
        var wewes: Array<Array<Int>> = Array(g.psize, { Array(g.psize, { 0 }) })//оценка веса поля

        //диагонали
        for (i in 0..g.psize-g.needToWin) { //столбцы
            for (l in 0..g.psize-g.needToWin) { //строки
                var p: Int = 0 //
                var streak: Int = 0
                for (n in 0..g.needToWin-1) { //клетки линии
                    if (g.pole[i + n][l + n] != 0) {
                        if (p == 0) {
                            p = g.pole[i + n][l + n]
                        }
                        if (p == g.pole[i + n][l + n]) {
                            streak++
                        } else {
                            streak = -g.needToWin - 2
                        }
                    }
                }

                if (streak > 0) {
                    var streakF = streak.toFloat()
                    streakF = streakF.pow(streakF)
                    streak = streakF.toInt()
                    for (n in 0..g.needToWin-1) { //клетки линии
                        wewes[i + n][l + n] += streak
                    }
                }
            }
        }
        //диагонали
        for (i in 0..g.psize-g.needToWin) { //столбцы
            for (l in g.needToWin-1..g.psize-1) { //строки
                var p: Int = 0
                var streak: Int = 0
                for (n in 0..g.needToWin-1) { //клетки линии
                    if (g.pole[i + n][l - n] != 0) {
                        if (p == 0) {
                            p = g.pole[i + n][l - n]
                        }
                        if (p == g.pole[i + n][l - n]) {
                            streak++
                        } else {
                            streak = -g.needToWin - 2
                        }
                    }
                }

                if (streak > 0) {
                    var streakF = streak.toFloat()
                    streakF = streakF.pow(streakF)
                    streak = streakF.toInt()
                    for (n in 0..g.needToWin-1) { //клетки линии
                        wewes[i + n][l - n] += streak
                    }
                }
            }
        }

        //вертикали
        for (i in 0..g.psize-1) { //столбцы
            for (l in 0..g.psize-g.needToWin) { //строки
                var p: Int = 0
                var streak: Int = 0
                for (n in 0..g.needToWin-1) { //клетки линии
                    if (g.pole[i][l + n] != 0) {
                        if (p == 0) {
                            p = g.pole[i][l + n]
                        }
                        if (p == g.pole[i][l + n]) {
                            streak++
                        } else {
                            streak = -g.needToWin - 2
                        }
                    }
                }

                if (streak > 0) {
                    var streakF = streak.toFloat()
                    streakF = streakF.pow(streakF)
                    streak = streakF.toInt()
                    for (n in 0..g.needToWin-1) { //клетки линии
                        wewes[i][l + n] += streak
                    }
                }
            }
        }

        //горизонтали
        for (i in 0..g.psize-g.needToWin) { //столбцы
            for (l in 0..g.psize-1) { //строки
                var p: Int = 0
                var streak: Int = 0
                for (n in 0..g.needToWin-1) { //клетки линии
                    if (g.pole[i + n][l] != 0) {
                        if (p == 0) {
                            p = g.pole[i + n][l]
                        }
                        if (p == g.pole[i + n][l]) {
                            streak++
                        } else {
                            streak = -g.needToWin - 2
                        }
                    }
                }

                if (streak > 0) {
                    var streakF = streak.toFloat()
                    streakF = streakF.pow(streakF)
                    streak = streakF.toInt()
                    for (n in 0..g.needToWin-1) { //клетки линии
                        wewes[i + n][l] += streak
                    }
                }

            }
        }
        return bestchoice(wewes, g)
    }
    fun bestchoice(wewes: Array<Array<Int>>, g : Game) : Point // "il" - возвращает координаты
    {
        var BC = object { //bestChoice
            var ozh: Int = -1
            var i: Int = 0
            var l: Int = 0
        }

        for (i in 0..g.psize-1) {
            for (l in 0..g.psize-1) {
                if (wewes[i][l] > BC.ozh)
                {
                    if (g.pole[i][l] == 0)
                    {
                        BC.ozh = wewes[i][l]
                        BC.i = i
                        BC.l = l
                    }
                }
            }
        }

        return Point(BC.i, BC.l)
    }


}