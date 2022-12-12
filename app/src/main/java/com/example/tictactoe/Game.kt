package com.example.tictactoe



import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import com.example.tictactoe.databinding.ActivityGameBinding
import kotlinx.android.synthetic.main.activity_game.*


class Game : AppCompatActivity() {
    enum class Turn
    {
        P1,
        P2
    }

    private var firstTurn = Turn.P1
    private var currentTurn = Turn.P1

    private var player1Scores = 0
    private var player2Scores = 0

    private var boardList = mutableListOf<Button>()

    private lateinit var binding : ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        P1Name.text=intent.getStringExtra("P1")+": X"
        P2Name.text=intent.getStringExtra("P2")+": O"
        initBoard()

    }
    private fun initBoard()
    {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)
    }

    fun boardTapped(view: View)
    {
        if(view !is Button)
            return
        addToBoard(view)

        if(checkForVictory(P1))
        {
            player2Scores++
            result("${P1Name.text.substring(0, P1Name.length() - 3)} Win!")
        }
        else if(checkForVictory(P2))
        {
            player1Scores++
            result("${P2Name.text.substring(0, P2Name.length() - 3)} Win!")
        }

        if(fullBoard())
        {
            result("Draw")
        }

    }

    private fun checkForVictory(s: String): Boolean
    {
        //Horizontal Victory
        if(match(binding.a1,s) && match(binding.a2,s) && match(binding.a3,s))
            return true
        if(match(binding.b1,s) && match(binding.b2,s) && match(binding.b3,s))
            return true
        if(match(binding.c1,s) && match(binding.c2,s) && match(binding.c3,s))
            return true

        //Vertical Victory
        if(match(binding.a1,s) && match(binding.b1,s) && match(binding.c1,s))
            return true
        if(match(binding.a2,s) && match(binding.b2,s) && match(binding.c2,s))
            return true
        if(match(binding.a3,s) && match(binding.b3,s) && match(binding.c3,s))
            return true

        //Diagonal Victory
        if(match(binding.a1,s) && match(binding.b2,s) && match(binding.c3,s))
            return true
        if(match(binding.a3,s) && match(binding.b2,s) && match(binding.c1,s))
            return true

        return false
    }

    private fun match(button: Button, symbol : String): Boolean = button.text == symbol

    private fun result(title: String)
    {
        val message = "\n${P1Name.text.substring(0, P1Name.length() - 3)}'s scores: $player2Scores\n\n${P2Name.text.substring(0, P2Name.length() - 3)}'s scores: $player1Scores"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Reset")
            { _,_ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()
    }

    private fun resetBoard()
    {
        for(button in boardList)
        {
            button.text = ""
        }

        if(firstTurn == Turn.P2)
            firstTurn = Turn.P1
        else if(firstTurn == Turn.P1)
            firstTurn = Turn.P2

        currentTurn = firstTurn
        setTurnLabel()
    }

    private fun fullBoard(): Boolean
    {
        for(button in boardList)
        {
            if(button.text == "")
                return false
        }
        return true
    }

    private fun addToBoard(button: Button)
    {
        if(button.text != "")
            return

        if(currentTurn == Turn.P1)
        {
            button.text = P1
            currentTurn = Turn.P2
        }
        else if(currentTurn == Turn.P2)
        {
            button.text = P2
            currentTurn = Turn.P1
        }
        setTurnLabel()
    }

    private fun setTurnLabel()
    {
        var turnText = ""
        if(currentTurn == Turn.P2)
            turnText = "Turn $P2"
        else if(currentTurn == Turn.P1)
            turnText = "Turn $P1"

        binding.turnTV.text = turnText
    }

    companion object {
        const val P1 = "x"
        const val P2 = "O"
    }
}
