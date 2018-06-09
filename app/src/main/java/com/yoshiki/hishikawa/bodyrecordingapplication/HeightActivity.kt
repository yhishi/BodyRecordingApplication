package com.yoshiki.hishikawa.bodyrecordingapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_height.*

class HeightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height)

        // スピナーがクリック時の処理
        spinner.onItemSelectedListener =
                // TODO ？有識者に聞く。spinnerのonItemSelectedListenerプロパティにAdapterView.OnItemSelectedListenerのインタフェースを実装したobjectを設定で合ってる？？
                object : AdapterView.OnItemSelectedListener {

                    // itemが選択がされた場合
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val spinner = parent as? Spinner
                        val item = spinner?.selectedItem as? String
                        item?.let {
                            // 選択された身長を画面に表示
                            if (it.isNotEmpty()) height.text = it
                        }
                    }

                    // itemが選択がされなかった場合
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }

        // 共有プリファレンスの値を画面表示
        PreferenceManager.getDefaultSharedPreferences(this).apply {
            val heightVal = getInt("HEIGHT", 160)
            height.text = heightVal.toString()
            seekBar.progress = heightVal
        }

        // シークバーの値を変更した時の処理
        seekBar.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {

                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        height.text = progress.toString()
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
                }
        )

        // ラジオボタンタップ時の処理
        radioGroup.setOnCheckedChangeListener {
            group, checkedId ->
            height.text = findViewById<RadioButton>(checkedId).text
        }

        // ↑上記 ラジオボタンタップ時の処理は元は↓。↑はKotlinのSAM変換。onCheckedChangedメソッド一つのため可能。
//        radioGroup.setOnCheckedChangeListener (
//                object : RadioGroup.OnCheckedChangeListener {
//                    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
//                        height.text = findViewById<RadioButton>(checkedId).text
//                    }
//
//                }
//        )



    }
    // アクティビティが非表示になった場合、共有プリファレンスに保存
    override fun onPause() {
        super.onPause()
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt("HEIGHT", height.text.toString().toInt())
                .apply()
    }

}
