package com.example.myapplication

import android.content.Context
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow

class Main2Activity : AppCompatActivity() {


    var ai1 : AiTicTacToe = AiTicTacToe()
    var g : Game = Game(ai1, ai1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewttt.g = g


        viewttt.setOnTouchListener(
            object : View.OnTouchListener {
                override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                    var p : PointF = PointF()
                    if (p1 != null) {
                        p.x = p1.y / (viewttt.height / g.psize)
                        p.y = p1.x / (viewttt.width / g.psize)
                        Log.d("POSITION", "${viewttt.width} , ${viewttt.height}")

                        g.roundUser(p) ///////////////////////////////////////////////////////////////////////

                        Log.d("POSITION", "$p")
                        Log.d("POSITION", "${p1.x} , ${p1.y}")
                    }
                    return true
                }

            })
    }
    fun bclick(v: View)
    {
        g.round()
        g.output()
        if (g.check()) {
            textView.setText("Победил игрок номер " + g.winner?.toString())
        }

    }

}





class AiTicTacToe {
    fun evaluation(g: Game): Point { // "il" - возвращает координаты
        var wewes: Array<Array<Int>> = Array(g.psize, { Array(g.psize, { 0 }) })//оценка веса поля

        //диагонали
        for (i in 0..g.psize - g.needToWin) { //столбцы
            for (l in 0..g.psize - g.needToWin) { //строки
                var p: Int = 0 //
                var streak: Int = 0
                for (n in 0..g.needToWin - 1) { //клетки линии
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
                    for (n in 0..g.needToWin - 1) { //клетки линии
                        wewes[i + n][l + n] += streak
                    }
                }
            }
        }
        //диагонали
        for (i in 0..g.psize - g.needToWin) { //столбцы
            for (l in g.needToWin - 1..g.psize - 1) { //строки
                var p: Int = 0
                var streak: Int = 0
                for (n in 0..g.needToWin - 1) { //клетки линии
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
                    for (n in 0..g.needToWin - 1) { //клетки линии
                        wewes[i + n][l - n] += streak
                    }
                }
            }
        }

        //вертикали
        for (i in 0..g.psize - 1) { //столбцы
            for (l in 0..g.psize - g.needToWin) { //строки
                var p: Int = 0
                var streak: Int = 0
                for (n in 0..g.needToWin - 1) { //клетки линии
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
                    for (n in 0..g.needToWin - 1) { //клетки линии
                        wewes[i][l + n] += streak
                    }
                }
            }
        }

        //горизонтали
        for (i in 0..g.psize - g.needToWin) { //столбцы
            for (l in 0..g.psize - 1) { //строки
                var p: Int = 0
                var streak: Int = 0
                for (n in 0..g.needToWin - 1) { //клетки линии
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
                    for (n in 0..g.needToWin - 1) { //клетки линии
                        wewes[i + n][l] += streak
                    }
                }

            }
        }
    }


    class Game(val p1: AiTicTacToe, val p2: AiTicTacToe) {
        var winner: Int? = null
        var psize: Int = 10
        var needToWin: Int = 5
        var pole: Array<Array<Int>> = Array(psize, { Array(psize, { 0 }) })
        var turn: Int = 0

        fun round() {
            if (!check()) {
                var t: Point = p1.evaluation(this)
                this.pole[t.x][t.y] = turn % 2 + 1
                turn++
            }
        }

        fun roundUser(t: PointF) {
            if (this.pole[t.x.toInt()][t.y.toInt()] == 0 && (!check())) {
                this.pole[t.x.toInt()][t.y.toInt()] = turn % 2 + 1
                turn++
                output() ///////////////////////////////////////////////////////////////////////////////

                if (this.check()) {
                    Log.d("CONTROLLA", "ПОБЕДИЛ ИГРОК \n"); while (false) {
                    }
                }
                round() ////////////////////////////////////////////////////////////////////////////////
                output()
                if (this.check()) {
                    Log.d("CONTROLLA", "ПОБЕДИЛ КОМПУДАКТЕР \n"); while (false) {
                    }
                }
            }
        }

        fun output() {
            for (i in 0..this.psize - 1) { //столбцы
                var ou: String = ""
                for (l in 0..this.psize - 1) { //строки
                    ou += " " + this.pole[i][l].toString()
                }
                Log.d("CONTROLLA", "$ou       $i\n")
            }
            Log.d("CONTROLLA", "\n\n\n\n+")
        }

        fun check(): Boolean {


            //диагонали
            for (i in 0..this.psize - this.needToWin) { //столбцы
                for (l in this.needToWin - 1..this.psize - 1) { //строки
                    for (p in 1..2) { //игроки

                        var v: Int = 0 //верные клетки в линии
                        for (n in 0..needToWin - 1) { //клетки линии
                            if (pole[i + n][l - n] == p) {
                                v++
                            }
                        }
                        if (v == needToWin) {
                            winner = p
                            return true
                        } // функция победы игрока номер P

                    }
                }
            }

            //вертикали
            for (i in 0..this.psize - 1) { //столбцы
                for (l in 0..this.psize - this.needToWin) { //строки
                    for (p in 1..2) { //игроки

                        var v: Int = 0 //верные клетки в линии
                        for (n in 0..needToWin - 1) { //клетки линии
                            if (pole[i][l + n] == p) {
                                v++
                            }
                        }
                        if (v == needToWin) {
                            winner = p
                            return true
                        } // функция победы игрока номер P

                    }
                }
            }

            //горизонтали
            for (i in 0..this.psize - this.needToWin) { //столбцы
                for (l in 0..this.psize - 1) { //строки
                    for (p in 1..2) { //игроки

                        var v: Int = 0 //верные клетки в линии
                        for (n in 0..needToWin - 1) { //клетки линии
                            if (pole[i + n][l] == p) {
                                v++
                            }
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

        fun draw(canvas: Canvas) {
            var brus: Paint = Paint()
            brus.strokeWidth = 20f
            for (i in 0..psize) {
                canvas.drawLine(
                    i * (canvas.width / psize).toFloat(), 0f, i * (canvas.width / psize).toFloat(),
                    canvas.height.toFloat(), brus
                )

            }
            for (i in 0..psize) {
                canvas.drawLine(
                    0f, i * (canvas.height / psize).toFloat(),
                    canvas.width.toFloat(), i * (canvas.height / psize).toFloat(), brus
                )
            }



            for (x in 0..psize - 1) {
                for (y in 0..psize - 1) {
                    if (pole[x][y] == 1) {
                        brus.color = Color.BLUE  //BLUE O
                        canvas.drawCircle(
                            y.toFloat() * (canvas.width / psize) + canvas.width / psize / 2,
                            x.toFloat() * (canvas.height / psize) + canvas.height / psize / 2,
                            (canvas.width / psize / 2).toFloat(),
                            brus
                        )
                    } else if (pole[x][y] == 2) {
                        brus.color = Color.RED  //RED X
                        canvas.drawLine(
                            y.toFloat() * (canvas.width / psize),
                            x.toFloat() * (canvas.height / psize),
                            (y + 1).toFloat() * (canvas.width / psize),
                            (x + 1).toFloat() * (canvas.height / psize),
                            brus
                        )
                        canvas.drawLine(
                            (y + 1).toFloat() * (canvas.width / psize),
                            x.toFloat() * (canvas.height / psize),
                            y.toFloat() * (canvas.width / psize),
                            (x + 1).toFloat() * (canvas.height / psize),
                            brus
                        )
                    }
                }
            }
            checkline(canvas)
        }

        fun checkline(canvas: Canvas): Boolean { //диагонали
            for (i in 0..this.psize - this.needToWin) { //столбцы
                for (l in 0..this.psize - this.needToWin) { //строки
                    for (p in 1..2) { //игроки

                        var v: Int = 0 //верные клетки в линии
                        for (n in 0..needToWin - 1) { //клетки линии
                            if (pole[i + n][l + n] == p) {
                                v++
                            }
                        }
                        if (v == needToWin) {
                            var brus: Paint = Paint()
                            brus.color = Color.GREEN  //RED X
                            brus.strokeWidth = 40f
                            canvas.drawLine(
                                (l.toFloat() + 0.5f) * (canvas.width / psize),
                                (i.toFloat() + 0.5f) * (canvas.height / psize),
                                ((l + needToWin).toFloat() - 0.5f) * (canvas.width / psize),
                                ((i + needToWin).toFloat() - 0.5f) * (canvas.height / psize),
                                brus
                            )
                            return true
                        }
                    } // функция победы игрока номер P

                }
            }


            //диагонали
            for (i in 0..this.psize - this.needToWin) { //столбцы
                for (l in this.needToWin - 1..this.psize - 1) { //строки
                    for (p in 1..2) { //игроки

                        var v: Int = 0 //верные клетки в линии
                        for (n in 0..needToWin - 1) { //клетки линии
                            if (pole[i + n][l - n] == p) {
                                v++
                            }
                        }
                        if (v == needToWin) {
                            var brus: Paint = Paint()
                            brus.color = Color.GREEN  //RED X
                            brus.strokeWidth = 40f
                            canvas.drawLine(
                                ((l - needToWin).toFloat() + 1.5f) * (canvas.width / psize),
                                ((i + needToWin).toFloat() - 0.5f) * (canvas.height / psize),
                                (l.toFloat() + 0.5f) * (canvas.width / psize),
                                (i.toFloat() + 0.5f) * (canvas.height / psize),
                                brus
                            )
                            return true
                        } // функция победы игрока номер P

                    }
                }
            }

            //вертикали
            for (i in 0..this.psize - 1) { //столбцы
                for (l in 0..this.psize - this.needToWin) { //строки
                    for (p in 1..2) { //игроки

                        var v: Int = 0 //верные клетки в линии
                        for (n in 0..needToWin - 1) { //клетки линии
                            if (pole[i][l + n] == p) {
                                v++
                            }
                        }
                        if (v == needToWin) {
                            var brus: Paint = Paint()
                            brus.color = Color.GREEN  //RED X
                            brus.strokeWidth = 40f
                            canvas.drawLine(
                                (l.toFloat() + 0.5f) * (canvas.width / psize),
                                (i.toFloat() + 0.5f) * (canvas.height / psize),
                                ((l + needToWin).toFloat() - 0.5f) * (canvas.width / psize),
                                (i.toFloat() + 0.5f) * (canvas.height / psize),
                                brus
                            )
                            return true
                        } // функция победы игрока номер P

                    }
                }
            }

            //горизонтали
            for (i in 0..this.psize - this.needToWin) { //столбцы
                for (l in 0..this.psize - 1) { //строки
                    for (p in 1..2) { //игроки


                        for (n in 0..needToWin - 1) { //клетки линии
                            if (pole[i + n][l] == p) {
                                v++
                            }
                        }
                        if (v == needToWin) {
                            var brus: Paint = Paint()
                            brus.color = Color.GREEN  //RED X
                            brus.strokeWidth = 40f
                            canvas.drawLine(
                                (l.toFloat() + 0.5f) * (canvas.width / psize),
                                (i.toFloat() + 0.5f) * (canvas.height / psize),
                                (l.toFloat() + 0.5f) * (canvas.width / psize),
                                ((i + needToWin).toFloat() - 0.5f) * (canvas.height / psize),
                                brus
                            )
                            return true
                        } // функция победы игрока номер P

                    }
                }
            }
            return false
        }
    }

    class ViewTTT(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
        var g: Game? = null
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            g?.draw(canvas)
            invalidate()
        }
    }
}