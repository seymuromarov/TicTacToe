package info.seymur.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
import android.R.attr.button
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import org.jetbrains.anko.toast
import android.support.v4.content.ContextCompat
import com.sdsmdg.tastytoast.TastyToast
import info.seymur.tictactoe.getCell.GetCell
import info.seymur.tictactoe.operations.GetWinner
import java.util.*


class MultiPlayerActivity : AppCompatActivity() {

    var activePlayer: Int? = 1;
    //    var player1 = ArrayList<Int>()
//    var player2 = ArrayList<Int>()
    var player1Sign: String? = null
    var player2Sign: String? = null
    var emptyCells = ArrayList<Int>()
    var board_cell = Array<Int>(9) { 0 }
    val random = Random()
    var Buttons = ArrayList<Button>()
    var over: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val intent = intent
        player1Sign = intent.getStringExtra("sign")
        changeActivePlayerText()
        when (player1Sign) {
            "X" -> {
                player1Sign = "X"
                player2Sign = "O"
            }
            "O" -> {
                player1Sign = "O"
                player2Sign = "X"
            }
        }
        reset.setOnClickListener {
            reset()
        }
        button_add()
    }

    fun changeActivePlayerText() {
        turn.text = "Player${activePlayer} turn"
    }

    fun pressed(view: View) {
        var selected = view as Button
        val drawable = selected.background as GradientDrawable
        var cell = GetCell().id(selected)
        when (activePlayer) {
            1 -> {
                drawable.setStroke(2, Color.RED)
                selected.text = player1Sign
                selected.isEnabled = false
                selected.setTextColor(ContextCompat.getColorStateList(applicationContext, R.color.player1SignColor))
                board_cell[cell - 1] = 1
                activePlayer = 2
                changeActivePlayerText()
                check_winner()
            }
            2 -> {
                drawable.setStroke(2, Color.GREEN)
                selected.text = player2Sign
                selected.isEnabled = false
                selected.setTextColor(ContextCompat.getColorStateList(applicationContext, R.color.player2SignColor))
                board_cell[cell - 1] = 2
                activePlayer = 1
                changeActivePlayerText()
                check_winner()
            }
        }
    }

//    private fun reset() {
//
//        val intent = Intent(applicationContext, MultiPlayerActivity::class.java)
//        intent.putExtra("sign", player1Sign)
//        intent.putExtra("turn", activePlayer)
//        startActivity(intent);
//        finish()
//    }

    fun game_over() {
        over = true
        for (item in Buttons) {
            item.isEnabled = false
        }
    }

    fun reset() {
        over = false
        for (item in Buttons) {
            item.isEnabled = true
            item.text = ""
            var drawable = item.background as GradientDrawable
            drawable.setStroke(2, Color.BLUE)
        }
        for (i in 0..8) {
            board_cell[i] = 0
        }
    }

    fun button_add() {
        Buttons.add(b1)
        Buttons.add(b2)
        Buttons.add(b3)
        Buttons.add(b4)
        Buttons.add(b5)
        Buttons.add(b6)
        Buttons.add(b7)
        Buttons.add(b8)
        Buttons.add(b9)
    }

    private fun check_winner() {
        var winner = GetWinner().check(board_cell)
        when (winner) {
            1 -> {
                TastyToast.makeText(applicationContext, "Player 1 won the game", TastyToast.LENGTH_LONG,
                        TastyToast.INFO)
                game_over()
            }
            2 -> {
                TastyToast.makeText(applicationContext, "Player 2 won the game", TastyToast.LENGTH_LONG,
                        TastyToast.INFO)
                game_over()
            }
        }
    }

}
