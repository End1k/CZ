package com.example.myapplication

import android.graphics.*
import com.example.myapplication.player.Player

class Game(val p1n: Player, val p2n : Player){
    var p1 : Player = p1n
    var p2 : Player = p2n

    var winner: Int? = null
    var psize: Int = 10
    var needToWin : Int = 5
    var pole: Array<Array<Int>> = Array(psize, { Array(psize, {0})})
    var turn: Int = 0
    var thisPlayer: Int = 1

//    fun round(){
//        while (turn < psize*psize)
//        {
//            if (!check()) {
//                if (turn % 2 == 0) {
//                    var t: Point = p1.turning(this)
//                    this.pole[t.x][t.y] = turn % 2 + 1
//                    turn++
//                }
//
//                if (turn % 2 == 1 && turn < psize*psize) {
//                    var t: Point = p2.turning(this)
//                    this.pole[t.x][t.y] = turn % 2 + 1
//                    turn++
//                }
//            }
//        }
//    }

//    fun roundUser(t: Point){
//        if (thisplayer == 0)
//        {
//            p1.getCoordsOfTTC(this, t)
//        }
//        if (thisplayer == 1)
//        {
//            p2.getCoordsOfTTC(this, t)
//        }
//
//
//        if (this.pole[t.x.toInt()][t.y.toInt()] == 0 && (!check()))
//        {
//            this.pole[t.x.toInt()][t.y.toInt()] = turn % 2 + 1
//            turn++
//
//            if (this.check()) {}
//            round()
//            if (this.check()) {}
//        }
//    }

    fun check( ) : Boolean
    { //диагонали
        for (i in 0..this.psize-this.needToWin) { //столбцы
            for (l in 0..this.psize-this.needToWin) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l+n] == p) {v++}
                    }
                    if (v == needToWin) {
                        winner = p
                        return true
                    }
                } // функция победы игрока номер P

            }
        }


        //диагонали
        for (i in 0..this.psize-this.needToWin) { //столбцы
            for (l in this.needToWin-1..this.psize-1) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l-n] == p) {v++}
                    }
                    if (v == needToWin) {
                        winner = p
                        return true
                    } // функция победы игрока номер P

                }
            }
        }

        //вертикали
        for (i in 0..this.psize-1) { //столбцы
            for (l in 0..this.psize-this.needToWin) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i][l+n] == p) {v++}
                    }
                    if (v == needToWin) {
                        winner = p
                        return true
                    } // функция победы игрока номер P

                }
            }
        }

        //горизонтали
        for (i in 0..this.psize-this.needToWin) { //столбцы
            for (l in 0..this.psize-1) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l] == p) {v++}
                    }
                    if (v == needToWin) {
                        winner = p
                        return true
                    } // функция победы игрока номер P

                }
            }
        }
        return false
    }

    fun draw(canvas: Canvas){
        var brus: Paint = Paint()
        brus.strokeWidth = 20f
        for (i in 0..psize){
            canvas.drawLine(i*(canvas.width/psize).toFloat(), 0f, i*(canvas.width/psize).toFloat(),
                canvas.height.toFloat(),brus)

        }
        for (i in 0..psize) {
            canvas.drawLine(
                0f, i * (canvas.height / psize).toFloat(),
                canvas.width.toFloat(),i * (canvas.height/ psize).toFloat(), brus )
        }

        for (x in 0..psize-1)
        {
            for (y in 0..psize-1)
            {
                if (pole[x][y] == 1)
                {
                    brus.color = Color.BLUE  //BLUE O
                    canvas.drawCircle(y.toFloat() * (canvas.width / psize) + canvas.width / psize / 2  ,x.toFloat() * (canvas.height / psize) + canvas.height / psize / 2, (canvas.width / psize / 2).toFloat(), brus)
                }
                else if (pole[x][y] == 2)
                {
                    brus.color = Color.RED  //RED X
                    canvas.drawLine(y.toFloat() * (canvas.width / psize), x.toFloat() * (canvas.height / psize), (y+1).toFloat() * (canvas.width / psize), (x+1).toFloat() * (canvas.height / psize), brus)
                    canvas.drawLine((y+1).toFloat() * (canvas.width / psize), x.toFloat() * (canvas.height / psize), y.toFloat() * (canvas.width / psize), (x+1).toFloat() * (canvas.height / psize), brus)
                }
            }
        }
        checkline(canvas)
    }

    fun checkline(canvas : Canvas) : Boolean
    { //диагонали
        for (i in 0..this.psize-this.needToWin) { //столбцы
            for (l in 0..this.psize-this.needToWin) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l+n] == p) {v++}
                    }
                    if (v == needToWin) {
                        var brus : Paint = Paint()
                        brus.color = Color.GREEN  //RED X
                        brus.strokeWidth = 40f
                        canvas.drawLine((l.toFloat() + 0.5f) * (canvas.width / psize), (i.toFloat() + 0.5f) * (canvas.height / psize), ((l+needToWin).toFloat() - 0.5f) * (canvas.width / psize), ((i+needToWin).toFloat() - 0.5f) * (canvas.height / psize), brus)
                        return true
                    }
                } // функция победы игрока номер P

            }
        }


        //диагонали
        for (i in 0..this.psize-this.needToWin) { //столбцы
            for (l in this.needToWin-1..this.psize-1) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l-n] == p) {v++}
                    }
                    if (v == needToWin) {
                        var brus : Paint = Paint()
                        brus.color = Color.GREEN  //RED X
                        brus.strokeWidth = 40f
                        canvas.drawLine(((l-needToWin).toFloat() + 1.5f) * (canvas.width / psize), ((i+needToWin).toFloat() - 0.5f) * (canvas.height / psize), (l.toFloat() + 0.5f) * (canvas.width / psize), (i.toFloat() + 0.5f) * (canvas.height / psize), brus)
                        return true
                    } // функция победы игрока номер P

                }
            }
        }

        //вертикали
        for (i in 0..this.psize-1) { //столбцы
            for (l in 0..this.psize-this.needToWin) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i][l+n] == p) {v++}
                    }
                    if (v == needToWin) {
                        var brus : Paint = Paint()
                        brus.color = Color.GREEN  //RED X
                        brus.strokeWidth = 40f
                        canvas.drawLine((l.toFloat() + 0.5f) * (canvas.width / psize), (i.toFloat() + 0.5f) * (canvas.height / psize), ((l+needToWin).toFloat() - 0.5f) * (canvas.width / psize), (i.toFloat() + 0.5f) * (canvas.height / psize), brus)
                        return true
                    } // функция победы игрока номер P

                }
            }
        }

        //горизонтали
        for (i in 0..this.psize-this.needToWin) { //столбцы
            for (l in 0..this.psize-1) { //строки
                for (p in 1..2) { //игроки

                    var v : Int = 0 //верные клетки в линии
                    for (n in 0..needToWin-1) { //клетки линии
                        if (pole[i+n][l] == p) {v++}
                    }
                    if (v == needToWin) {
                        var brus : Paint = Paint()
                        brus.color = Color.GREEN  //RED X
                        brus.strokeWidth = 40f
                        canvas.drawLine((l.toFloat() + 0.5f) * (canvas.width / psize), (i.toFloat() + 0.5f) * (canvas.height / psize), (l.toFloat() + 0.5f) * (canvas.width / psize), ((i+needToWin).toFloat() - 0.5f) * (canvas.height / psize), brus)
                        return true
                    } // функция победы игрока номер P

                }
            }
        }
        return false
    }
}