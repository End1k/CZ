package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Mainest2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainest2)
    }

    fun toComputer(v: View)
    {
        var next: Intent = Intent(this, MainActivity::class.java)
        startActivity(next)
    }
    fun toPlayer(v: View)
    {
        var next: Intent = Intent(this, vsRealPlayerActivity::class.java)
        startActivity(next)
    }
    fun toEnternet(v: View)
    {
        var next: Intent = Intent(this, vsRealPlayerActivityEnternet::class.java)
        startActivity(next)
    }
}
