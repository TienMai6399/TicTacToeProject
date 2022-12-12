package com.example.tictactoe

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_input_name.*

class InputName : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_name)
        Play.setOnClickListener {
            val intent= Intent(this, Game::class.java)
            intent.putExtra("P1", player1.text.toString())
            intent.putExtra("P2", player2.text.toString())
            startActivity(intent)
        }
        inputBack.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}