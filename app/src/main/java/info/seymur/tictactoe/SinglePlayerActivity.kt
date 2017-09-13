package info.seymur.tictactoe

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Button
import com.sdsmdg.tastytoast.TastyToast
import info.seymur.tictactoe.getCell.GetCell
import info.seymur.tictactoe.operations.GetWinner
import kotlinx.android.synthetic.main.activity_game.*
import org.jetbrains.anko.toast
import java.util.*

class SinglePlayerActivity : AppCompatActivity() {

    var activePlayer: Int? = 1;
    var level: Int? = 1;
    var emptyCells = ArrayList<Int>()
    var playerSign: String? = null
    var computerSign: String? = null
    var board_cell = Array<Int>(9) { 0 }
    val random = Random()
    var Buttons = ArrayList<Button>()
    var over: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val intent = intent
        playerSign = intent.getStringExtra("sign")
        level = intent.getStringExtra("level").toInt()

        changeActivePlayerText()
        when (playerSign) {
            "X" -> {
                playerSign = "X"
                computerSign = "O"
            }
            "O" -> {
                playerSign = "O"
                computerSign = "X"
            }
        }
        button_add()
        reset.setOnClickListener() {
            reset()
        }
    }

    fun changeActivePlayerText() {
        when (activePlayer) {
            1 -> turn.text = "Player turn"
            2 -> turn.text = "Computer turn"
        }
    }

    fun pressed(view: View) {
        var selected = view as Button
        play_game(selected)
    }

    fun play_game(selected: Button) {
        val drawable = selected.background as GradientDrawable

        when (activePlayer) {
            1 -> {
                drawable.setStroke(2, Color.RED)
                selected.text = playerSign
                selected.setTextColor(ContextCompat.getColorStateList(applicationContext, R.color.player1SignColor))
                selected.isEnabled = false
                activePlayer = 2

                var cell = GetCell().id(selected)
                board_cell[cell - 1] = 1
                check_winner()
                computer_logic()
            }
            2 -> {
                drawable.setStroke(2, Color.GREEN)
                selected.text = computerSign
                selected.isEnabled = false
                selected.setTextColor(ContextCompat.getColorStateList(applicationContext, R.color.player2SignColor))
                activePlayer = 1

                var cell = GetCell().id(selected)
                board_cell[cell - 1] = 2
                check_winner()
            }
        }
    }

    private fun computer_logic() {
        if (level == 1) {
            for (i in 0..8) {
                if (board_cell[i] == 0) {
                    emptyCells.add(i)
                }
            }

            if (emptyCells.size == 0)
                return
            else if (emptyCells.size == 1) {
                when (emptyCells[0]) {
                    0 -> play_game(b1)
                    1 -> play_game(b2)
                    2 -> play_game(b3)
                    3 -> play_game(b4)
                    4 -> play_game(b5)
                    5 -> play_game(b6)
                    6 -> play_game(b7)
                    7 -> play_game(b8)
                    8 -> play_game(b9)
                }
                return
            }

            var cell: Int = random.nextInt(emptyCells.size - 1) + 1;
            Log.e("erroram", "error $cell")
            if (!over) {
                when (emptyCells[cell]) {
                    0 -> play_game(b1)
                    1 -> play_game(b2)
                    2 -> play_game(b3)
                    3 -> play_game(b4)
                    4 -> play_game(b5)
                    5 -> play_game(b6)
                    6 -> play_game(b7)
                    7 -> play_game(b8)
                    8 -> play_game(b9)
                }
            }

            emptyCells.clear()
        }
    }

    private fun check_winner() {
        var winner = GetWinner().check(board_cell)
        when (winner) {
            1 -> {
                TastyToast.makeText(applicationContext, "Player won the game", TastyToast.LENGTH_LONG,
                        TastyToast.SUCCESS)
                game_over()
            }
            2 -> {
                TastyToast.makeText(applicationContext, "Computer won the game", TastyToast.LENGTH_LONG,
                        TastyToast.INFO)
                game_over()
            }
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
        if (activePlayer == 2) {
            computer_logic()
        }
    }

}
