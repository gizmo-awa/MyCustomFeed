package com.example.mycustomfeed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.lang.IllegalArgumentException

class Actividad3 : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad3)

        val myBtn = findViewById<Button>(R.id.button_BackOne)
        myBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent = when(view.id){
            R.id.button_BackOne -> Intent(this, MainActivity::class.java)
            else -> throw IllegalArgumentException("Invalid button")
        }
        startActivity(intent)
    }
}