package com.example.tippy

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


private const val TAG = "MainActivity"
private const val INITIAL_TIP_PERCENT = 15

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarTip.progress = INITIAL_TIP_PERCENT
        tvTipPercent.text = "$INITIAL_TIP_PERCENT%"
        updateTipDescription(INITIAL_TIP_PERCENT)
        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")
                tvTipPercent.text = "$progress%"
                updateTipDescription(progress)
                computeTipAndTotal()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
        etBase.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "afterTextChanges $s")
                computeTipAndTotal()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
             }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        val spinner: Spinner = findViewById(R.id.spinnerSplitBill)
        spinner.setSelection(0);
        val num_people = arrayOf(1,2,3,4,5,6,7,8)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            num_people
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter;

        spinnerSplitBill.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                splitBill()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })


    }


    private fun updateTipDescription(tipPercent: Int) {
        val tipDescription: String
        when (tipPercent) {
            in 0..9 -> tipDescription = "\uD83D\uDC4E"
            in 10..14 -> tipDescription = "\uD83D\uDE10"
            in 15..19 -> tipDescription = "\uD83D\uDE42"
            in 20..24 -> tipDescription = "\uD83D\uDE00"
            else -> tipDescription = "\uD83D\uDCAF"
        }
        tvTipDescription.text = tipDescription
        val color = ArgbEvaluator().evaluate(tipPercent.toFloat() / seekBarTip.max,
            ContextCompat.getColor(this, R.color.colorWorstTip),
            ContextCompat.getColor(this, R.color.colorBestTip)) as Int
        tvTipDescription.setTextColor(color)
    }
    private fun splitBill() {
        if(tvTotalAmount.text.isEmpty()){
            return
        }
        val num_people = spinnerSplitBill.getSelectedItem().toString().toInt()
        val amt_per_person = tvTotalAmount.text.toString().toDouble() / num_people
        tvAmtPerPerson.text = "%.2f".format(amt_per_person)

    }

    private fun computeTipAndTotal() {
       // Get the value of the base and tip percent
        if(etBase.text.isEmpty()){
            tvTipAmount.text = ""
            tvTotalAmount.text = ""
            tvAmtPerPerson.text = ""
            return
        }
        val baseAmount = etBase.text.toString().toDouble()
        val tipPercent = seekBarTip.progress
        val tipAmount = baseAmount * tipPercent / 100
        val totalAmount = baseAmount + tipAmount
        tvTipAmount.text = "%.2f".format(tipAmount)
        tvTotalAmount.text = "%.2f".format(totalAmount)
        splitBill()
    }
}
