package com.yoshiki.hishikawa.bodyrecordingapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 共有プリファレンスに保存されている値を表示
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.apply {
            val editNeck = getString("NECK", "")
            val editSleeve = getString("SLEEVE", "")
            val editWaist = getString("WAIST", "")
            val editInseam = getString("INSEAM", "")

            neck.setText(editNeck)
            sleeve.setText(editSleeve)
            waist.setText(editWaist)
            inseam.setText(editInseam)
        }

        // 入力内容を共有プリファレンスに保存
        save.setOnClickListener{ onSaveTapped() }

        // 身長入力画面に遷移
        heightButton.setOnClickListener {
            startActivity<HeightActivity>()  // AnkoのstartActivityでIntentを省略
        }
    }

    /**
     * onSaveTapped
     *
     * 共有プリファレンスに保存
     */
    private fun onSaveTapped() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        editor.putString("NECK", neck.text.toString())
                .putString("SLEEVE", sleeve.text.toString())
                .putString("WAIST", waist.text.toString())
                .putString("INSEAM", inseam.text.toString())
                .apply()

        Toast.makeText(this, "保存しました", Toast.LENGTH_LONG).show();
    }
}
