package info.seymur.tictactoe.getCell

import android.widget.Button
import info.seymur.tictactoe.R

open class GetCell() {
    init {

    }

    open fun id(buttonID: Button): Int {

        return when (buttonID.id) {
            R.id.b1 -> 1
            R.id.b2 -> 2
            R.id.b3 -> 3
            R.id.b4 -> 4
            R.id.b5 -> 5
            R.id.b6 -> 6
            R.id.b7 -> 7
            R.id.b8 -> 8
            R.id.b9 -> 9
            else -> 1
        }
    }
}