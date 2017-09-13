package info.seymur.tictactoe.operations

import com.sdsmdg.tastytoast.TastyToast

/**
 * Created by omaro on 9/13/2017.
 */
class GetWinner() {
    init {

    }

    fun check(board_cell: Array<Int>): Int {
        var winner = 0
        if (
        (board_cell[0] == 1 && board_cell[1] == 1 && board_cell[2] == 1)
                ||
                (board_cell[3] == 1 && board_cell[4] == 1 && board_cell[5] == 1)
                ||
                (board_cell[6] == 1 && board_cell[7] == 1 && board_cell[8] == 1)
                ||
                (board_cell[0] == 1 && board_cell[3] == 1 && board_cell[6] == 1)
                ||
                (board_cell[1] == 1 && board_cell[4] == 1 && board_cell[7] == 1)
                ||
                (board_cell[2] == 1 && board_cell[5] == 1 && board_cell[8] == 1)
                ||
                (board_cell[0] == 1 && board_cell[4] == 1 && board_cell[8] == 1)
                ||
                (board_cell[2] == 1 && board_cell[4] == 1 && board_cell[6] == 1)
                ) {
            winner = 1
        }
        if (
        (board_cell[0] == 2 && board_cell[1] == 2 && board_cell[2] == 2)
                ||
                (board_cell[3] == 2 && board_cell[4] == 2 && board_cell[5] == 2)
                ||
                (board_cell[6] == 2 && board_cell[7] == 2 && board_cell[8] == 2)
                ||
                (board_cell[0] == 2 && board_cell[3] == 2 && board_cell[6] == 2)
                ||
                (board_cell[1] == 2 && board_cell[4] == 2 && board_cell[7] == 2)
                ||
                (board_cell[2] == 2 && board_cell[5] == 2 && board_cell[8] == 2)
                ||
                (board_cell[0] == 2 && board_cell[4] == 2 && board_cell[8] == 2)
                ||
                (board_cell[2] == 2 && board_cell[4] == 2 && board_cell[6] == 2)
                ) {
            winner = 2
        }
        return winner
    }
}