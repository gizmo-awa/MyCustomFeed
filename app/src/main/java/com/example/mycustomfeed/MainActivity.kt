package com.example.mycustomfeed

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoView = findViewById<VideoView>(R.id.videoView)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        val offlineUri = Uri.parse("android.resource://$packageName/${R.raw.videocute}")

        videoView.setMediaController(mediaController)
        videoView.setVideoURI(offlineUri)
        videoView.requestFocus()
        videoView.start()

        val myBtn1 = findViewById<Button>(R.id.button_GoTwo)
        val myBtn2 = findViewById<Button>(R.id.button_GoRss)
        val myBtn3 = findViewById<Button>(R.id.button_GoThree)
        myBtn1.setOnClickListener(this)
        myBtn2.setOnClickListener(this)
        myBtn3.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent = when(view.id){
            R.id.button_GoTwo -> Intent(this, Actividad2::class.java)
            R.id.button_GoRss -> Intent(this, rssNews::class.java)
            R.id.button_GoThree -> Intent(this,Actividad3::class.java)
            else -> throw IllegalArgumentException("Invalid button")
        }
        startActivity(intent)
    }
}