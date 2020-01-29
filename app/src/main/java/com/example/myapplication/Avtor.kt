package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.firebase.auth.FirebaseUser
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult


class Avtor : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avtor)
         mAuth = FirebaseAuth.getInstance()
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth?.getCurrentUser()
    //    updateUI(currentUser)
    }

    fun createAccount(email:String, password:String){
        mAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(this, OnCompleteListener { task ->
                if(task.isSuccessful()){
                    //var user: FirebaseUser = getCurrentUser().mAuth
                }
            })

    }

}
