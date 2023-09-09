package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var input:TextView? = null
    private var lastDot = false
    private var lastNumber = false
    private var alreadyDecimal = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input=findViewById(R.id.input)
    }

    fun workButton(view: View) {
        input?.append((view as Button).text)
        lastNumber=true
        lastDot=false
    }
    fun clearSc(view: View){
        input?.text=""
    }
    fun onDecimal(view: View){
        if(!lastDot && lastNumber && !alreadyDecimal){
            input?.append(".")
            lastDot=true
            lastNumber=false
            alreadyDecimal=true
        }
    }
    private fun isOperator(value : String): Boolean {
        return if(value.startsWith("-")){
            false
        } else {
                    (value.contains("+")||
                    value.contains("-")||
                    value.contains("*")||
                    value.contains("/"))
        }
    }

    fun onOperator(view: View){
        input?.text?.let{
            if(lastNumber && !isOperator(it.toString())){
                input?.append((view as Button).text)
                lastDot=false
                lastNumber=false
                alreadyDecimal=false

        }
        }
    }
    fun onEquals(view: View){
        if(lastNumber){
            var calc = input?.text.toString()
            var prefix=""
            try{
                if(calc.startsWith("-")){
                    calc=calc.substring(1)
                    prefix="-"
                }
                if(calc.contains("-")){
                val arr=calc.split("-")
                var one=arr[0]
                var two=arr[1]
                one=prefix+one
                input?.text=(one.toDouble()-two.toDouble()).toString()
                }
                else if(calc.contains("+")){
                    val arr=calc.split("+")
                    var one=arr[0]
                    var two=arr[1]
                    one=prefix+one
                    input?.text=(one.toDouble()+two.toDouble()).toString()
                }
                else if(calc.contains("*")){
                    val arr=calc.split("*")
                    var one=arr[0]
                    var two=arr[1]
                    one=prefix+one
                    input?.text=(one.toDouble()*two.toDouble()).toString()
                }
                else if(calc.contains("/")){
                    val arr=calc.split("/")
                    var one=arr[0]
                    var two=arr[1]
                    one=prefix+one
                    input?.text=(one.toDouble()/two.toDouble()).toString()
                }
                }
            catch(e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }


}
