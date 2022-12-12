package com.example.tictactoe

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PlayButton.setOnClickListener {
            val intent= Intent(this, InputName::class.java)
            startActivity(intent)
        }
        helpButton.setOnClickListener {
            val intent= Intent(this, Help::class.java)
            startActivity(intent)
        }



    }
}