package com.example.myapplication

import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.*
import android.util.Log





class MainActivity : AppCompatActivity() {

    var ai1 : AiTicTacToe = AiTicTacToe()
    var g : Game = Game(ai1, ai1)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun bclick(v:View)
    {
        g.round()
        g.output()
    }
}



class AiTicTacToe{
    fun evaluation( g : Game):Point { // "il" - возвращает координаты
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
            for (l in 1..g.psize-g.needToWin) { //строки
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
        for (i in 1..g.psize-g.needToWin) { //столбцы
            for (l in 1..g.psize-1) { //строки
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

        for (i in 1..g.psize-1) {
            for (l in 1..g.psize-1) {
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

class Game(val p1:AiTicTacToe, val p2 : AiTicTacToe){

    var psize: Int = 3
    var needToWin : Int = 3
    var pole: Array<Array<Int>> = Array(psize, { Array(psize, {0})})
    var turn: Int = 0

    fun round(){

        var t : Point = p1.evaluation(this)
        this.pole[t.x][t.y] = turn % 2 + 1
        turn++
    }

    fun output()
    {
        for (i in 0..this.psize - 1) { //столбцы
            var ou : String = ""
            for (l in 0..this.psize - 1) { //строки
                ou += " " + this.pole[i][l].toString()
            }
            Log.d("CONTROLLA","$ou \n")
        }
        Log.d("CONTROLLA","\n")
    }
}
//    fun buttonclick(v:View)
//    {
//        for (o in 1..64)
//        {
//            turnF()
//        }
//    }
//
//    fun turnF()
//    {
////        var t = choice()
////        g.pole[t[0].toInt() - '0'.toInt()][t[1].toInt() - '0'.toInt()] = turn % 2 + 1
////
////        var li = g.pole[1][1]
////
////        Log.d("CONTROLLA","$li")
////        turn++
////        Log.d("CONTROLLA","$t")
////
////        for (i in 1..8) { //столбцы
////            var ou : String = ""
////            for (l in 1..8) { //строки
////                ou += g.pole[i][l].toString()
////            }
////            Log.d("CONTROLLA","$ou   $i")
////        }
////    }
//
//    fun choice() : String // вернуть наилучший выбор компьютера
//    {
//        var BC = object { //bestChoice
//            var ozh: Int = -1
//            var i: Int = 0
//            var l: Int = 0
//        }
//
//        var wewes: Array<Array<Int>> = Array(9, { Array(9, {0}) })//оценка веса поля
//
//        //диагонали
//        for (i in 1..4) { //столбцы
//            for (l in 1..4) { //строки
//
//                var p : Int = 0 //
//                var streak : Int = 0
//                for (n in 0..4) { //клетки линии
//                    if (pole[i + n][l + n] != 0) {
//                        if (p == 0) {
//                            p = pole[i + n][l + n]
//                        }
//                        if (p == pole[i + n][l + n])
//                        {
//                            streak++
//                        }
//                        else {streak = -10}
//                    }
//                }
//
//                if (streak > 0)
//                {
//                    var streakF = streak.toFloat()
//                    streakF = streakF.pow(streakF)
//                    streak = streakF.toInt()
//                    for (n in 0..4) { //клетки линии
//                        wewes[i + n][l + n] += streak
//                    }
//                }
//            }
//        }
//
//        //диагонали
//        for (i in 1..4) { //столбцы
//            for (l in 5..8) { //строки
//                var p : Int = 0
//                var streak : Int = 0
//                for (n in 0..4) { //клетки линии
//                    if (pole[i + n][l - n] != 0) {
//                        if (p == 0) {
//                            p = pole[i + n][l - n]
//                        }
//                        if (p == pole[i + n][l - n])
//                        {
//                            streak++
//                        }
//                        else {streak = -10}
//                    }
//                }
//
//                if (streak > 0)
//                {
//                    var streakF = streak.toFloat()
//                    streakF = streakF.pow(streakF)
//                    streak = streakF.toInt()
//                    for (n in 0..4) { //клетки линии
//                        wewes[i + n][l - n] += streak
//                    }
//                }
//            }
//        }
//
//        //вертикали
//        for (i in 1..8) { //столбцы
//            for (l in 1..4) { //строки
//                var p : Int = 0
//                var streak : Int = 0
//                for (n in 0..4) { //клетки линии
//                    if (pole[i][l + n] != 0) {
//                        if (p == 0) {
//                            p = pole[i][l + n]
//                        }
//                        if (p == pole[i][l + n])
//                        {
//                            streak++
//                        }
//                        else {streak = -10}
//                    }
//                }
//
//                if (streak > 0)
//                {
//                    var streakF = streak.toFloat()
//                    streakF = streakF.pow(streakF)
//                    streak = streakF.toInt()
//                    for (n in 0..4) { //клетки линии
//                        wewes[i][l + n] += streak
//                    }
//                }
//            }
//        }
//
//        //горизонтали
//        for (i in 1..4) { //столбцы
//            for (l in 1..8) { //строки
//                var p : Int = 0
//                var streak : Int = 0
//                for (n in 0..4) { //клетки линии
//                    if (pole[i + n][l] != 0) {
//                        if (p == 0) {
//                            p = pole[i + n][l]
//                        }
//                        if (p == pole[i + n][l])
//                        {
//                            streak++
//                        }
//                        else {streak = -10}
//                    }
//                }
//
//                if (streak > 0)
//                {
//                    var streakF = streak.toFloat()
//                    streakF = streakF.pow(streakF)
//                    streak = streakF.toInt()
//                    for (n in 0..4) { //клетки линии
//                        wewes[i + n][l] += streak
//                    }
//                }
//            }
//        }
//
//
//        for (i in 1..8) {
//            for (l in 1..8) {
//                if (wewes[i][l] > BC.ozh)
//                {
//                    if (pole[i][l] == 0)
//                    {
//                        BC.ozh = wewes[i][l]
//                        BC.i = i
//                        BC.l = l
//                    }
//                }
//            }
//        }
//
//        var BCOI = BC.i.toString()
//        var BCOL = BC.l.toString()
//        var outputt : String = BCOI + BCOL
//        return outputt
//    }
//
//    tailrec fun findFixPoint(x: Double = 1.0): Double                //uselessssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss//
//            = if (x == Math.cos(x)) x else findFixPoint(Math.cos(x)) /////////////////////////////////////////////////////////////////////////////////
//
//    fun check(pole : Array<Array<Int>>) : Int
//    { //диагонали
//        for (i in 1..4) { //столбцы
//            for (l in 1..4) { //строки
//                for (p in 1..2) { //игроки
//
//                    var v : Int = 0 //верные клетки в линии
//                    for (n in 0..4) { //клетки линии
//                        if (pole[i+n][l+n] == p) {v++}
//                    }
//                    if (v == 5) {return p} // функция победы игрока номер P
//
//                }
//            }
//        }
//
//        //диагонали
//        for (i in 1..4) { //столбцы
//            for (l in 5..8) { //строки
//                for (p in 1..2) { //игроки
//
//                    var v : Int = 0 //верные клетки в линии
//                    for (n in 0..4) { //клетки линии
//                        if (pole[i+n][l-n] == p) {v++}
//                    }
//                    if (v == 5) {return p} // функция победы игрока номер P
//
//                }
//            }
//        }
//
//        //горизонтали
//        for (i in 1..8) { //столбцы
//            for (l in 1..4) { //строки
//                for (p in 1..2) { //игроки
//
//                    var v : Int = 0 //верные клетки в линии
//                    for (n in 0..4) { //клетки линии
//                        if (pole[i][l+n] == p) {v++}
//                    }
//                    if (v == 5) {return p} // функция победы игрока номер P
//
//                }
//            }
//        }
//
//        //вертикали
//        for (i in 1..4) { //столбцы
//            for (l in 1..8) { //строки
//                for (p in 1..2) { //игроки
//
//                    var v : Int = 0 //верные клетки в линии
//                    for (n in 0..4) { //клетки линии
//                        if (pole[i+n][l] == p) {v++}
//                    }
//                    if (v == 5) {return p} // функция победы игрока номер P
//
//                }
//            }
//        }
//        return 0
//    }