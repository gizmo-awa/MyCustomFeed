package com.example.mycustomfeed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.lang.IllegalArgumentException

class Actividad2 : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad2)
        val myBtn1 = findViewById<Button>(R.id.button_GoOne)
        myBtn1.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent = when(view.id){
            R.id.button_GoOne -> Intent(this, MainActivity::class.java)
            else -> throw IllegalArgumentException("Invalid button")
        }
        startActivity(intent)
    }
}