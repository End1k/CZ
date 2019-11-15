package com.example.myapplication.controller

import android.graphics.Point
import com.example.myapplication.Game
import com.google.firebase.database.*

class NetworkController(gn: Game) : Controller(gn) {
    var database: FirebaseDatabase =  FirebaseDatabase.getInstance()
    var myRef : DatabaseReference = database.getReference()
    init {
        myRef.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    override fun turni(p: Point) {
        myRef.push().setValue(p)
    }
}