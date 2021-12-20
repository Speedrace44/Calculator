package com.example.firstcalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    var oper : String = ""
    var operations = mutableListOf<String>("0")
    var modified = false
    @SuppressLint("SetTextI18n")
    public fun removeDecimal(str : Float): String{
        if(str.toString().length > 1){
            if(str.toString().takeLast(2) == ".0")
            {
                return str.toInt().toString()
            }
            else{
                return str.toString()
            }
        }
        else{
            return str.toString()
        }
    }
    @SuppressLint("SetTextI18n")
    public fun append(view: View){
        var baseNum = findViewById<TextView>(R.id.textView) as TextView
        var b: Button = view as Button

        if (b.id == R.id.addition || b.id == R.id.subtraction || b.id == R.id.multiplication || b.id == R.id.division) {
            when (b.id) {
                R.id.addition -> oper = "addition"
                R.id.subtraction -> oper = "subtraction"
                R.id.multiplication -> oper = "multiplication"
                else -> {
                    oper = "division"
                }
            }
            if (operations.size == 1) {
                operations.add(oper)
            } else if (operations.size == 2) {
                operations[1] = oper
            } else{
                var result = 0.0f
                if (operations[1] == "addition") {
                    result = operations[0].toFloat() + operations[2].toFloat()
                } else if (operations[1] == "subtraction") {
                    result = operations[0].toFloat() - operations[2].toFloat()
                } else if (operations[1] == "multiplication") {
                    result = operations[0].toFloat() * operations[2].toFloat()
                } else if (operations[1] == "division") {
                    result = operations[0].toFloat() / operations[2].toFloat()
                }
                baseNum.text = removeDecimal(result)
                operations.clear()
                operations.add(result.toString())
                operations.add(oper)
            }
        } else if (b.id == R.id.equals) {
            if (operations.size == 3) {
                var result = 0.0f
                if (operations[1] == "addition") {
                    result = operations[0].toFloat() + operations[2].toFloat()
                } else if (operations[1] == "subtraction") {
                    result = operations[0].toFloat() - operations[2].toFloat()
                } else if (operations[1] == "multiplication") {
                    result = operations[0].toFloat() * operations[2].toFloat()
                } else if (operations[1] == "division") {
                    result = operations[0].toFloat() / operations[2].toFloat()
                }
                baseNum.text = removeDecimal(result)
                operations.clear()
                operations.add(result.toString())
            }
        } else {
            val digit = Integer.parseInt(b.text.toString())
            if (operations.size == 1) {
                if("." in baseNum.text){
                    operations[0] = operations[0] + digit.toString()
                    baseNum.text = baseNum.text.toString() + "$digit"
                }
                else {
                    operations[0] = removeDecimal((10 * operations[0].toFloat()) + digit)
                    baseNum.text = removeDecimal((10 * baseNum.text.toString().toFloat()) + digit)
                }
            } else if (operations.size == 2) {
                operations.add(digit.toString())
                baseNum.text = digit.toString()
            } else {
                if("." in baseNum.text){
                    operations[2] = operations[2] + digit.toString()
                    baseNum.text = baseNum.text.toString() + "$digit"
                }
                else {
                    operations[2] = removeDecimal(((10 * operations[2].toFloat()) + digit))
                    baseNum.text = removeDecimal((10 * baseNum.text.toString().toFloat()) + digit)
                }
            }
        }
    }

    public fun percent(view : View){
        var baseNum = findViewById<TextView>(R.id.textView) as TextView
        if(operations.size == 3){
            operations[2] = (operations[2].toFloat() / 100.0f).toString()
            baseNum.text = (operations[2].toFloat() / 100.0f).toString()
        }
        else{
            operations[0] = (operations[0].toFloat() / 100.0f).toString()
            baseNum.text = (operations[0].toFloat() / 100.0f).toString()
        }
    }

    @SuppressLint("SetTextI18n")
    public fun changeSign(view : View){
        var baseNum = findViewById<TextView>(R.id.textView) as TextView
        if(baseNum.text.toString()[0] == '-'){
            baseNum.text = baseNum.text.takeLast(baseNum.text.length - 1)
        }
        else baseNum.text = "-${baseNum.text}"
    }

    public fun clearBase(view : View){
        var baseNum = findViewById<TextView>(R.id.textView) as TextView
        if(operations.size == 1){
            operations[0] = "0"
            baseNum.text = "0"
        }
        else if(operations.size == 2){
            baseNum.text = "0"
        }
        else{
            operations[2] = "0"
            baseNum.text = "0"
        }
    }

    @SuppressLint("SetTextI18n")
    public fun addDecimal(view : View){
        var baseNum = findViewById<TextView>(R.id.textView) as TextView
        if(operations.size == 2){
            baseNum.text = "0."
        }
        else if(operations.size == 1){
            if("." !in operations[0]){
                operations[0] = "${operations[0]}."
                baseNum.text = "${baseNum.text}."
            }
        }
        else{
            if("." !in operations[2]){
                operations[2] = "${operations[2]}."
                baseNum.text = "${baseNum.text}."
            }
        }
    }
}