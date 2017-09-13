package info.seymur.tictactoe

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.sdsmdg.tastytoast.TastyToast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {
    var mode: Int? = null
    var level: Int? = null
    var sign: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun select_mode(view: View) {
        val selected = view as ImageView
        when (selected.tag) {
            "single" -> {
                mode = 1;
                change_icons_and_text()
            }
            "multi" -> {
                mode = 2;
                change_icons_and_text()
            }
            "cross" -> {
                sign = "X"
                check_mode()
            }
            "circle" -> {
                sign = "O"
                check_mode()
            }
            "normal_level" -> {
                level = 1
                start_single_player()
            }
            "hard_level" -> {
                TastyToast.makeText(getApplicationContext(), "It isn't ready yet :)", TastyToast.LENGTH_LONG,
                        TastyToast.CONFUSING);
//                level = 2
//                start_single_player()
            }
        }
    }

    protected fun change_icons_and_text() {
        top.tag = "cross"
        bottom.tag = "circle"
        homeText.text = "Choose sign"
        top.setImageResource(R.drawable.cross);
        bottom.setImageResource(R.drawable.circle);
    }

    private fun check_mode() {
        when (mode) {
            1 -> {
                top.tag = "normal_level"
                bottom.tag = "hard_level"
                homeText.text = "Choose level"
                top.setImageResource(R.drawable.normal_level);
                bottom.setImageResource(R.drawable.hard_level);
            }
            2 -> {
                val intent = Intent(applicationContext, MultiPlayerActivity::class.java)
                intent.putExtra("sign", sign.toString())
                startActivity(intent);
                finish()
            }
        }
    }

    fun start_single_player() {
        val intent = Intent(applicationContext, SinglePlayerActivity::class.java)
        intent.putExtra("sign", sign.toString())
        intent.putExtra("level", level.toString())
        startActivity(intent);
        finish()
    }

}

