package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.VelocityTracker
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var resultTracker: TextView
    private var operand1 : String = ""
    private var operand2 : String = ""
    private var operation: String = ""
    private var emptyNext: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
        resultTracker = findViewById(R.id.resultTracker)

    }



    fun numberClick(view: View){

        if(view is Button){

            var numPanel: String = resultTextView.text.toString()
            var newNum: String = view.text.toString()


            if((numPanel=="" || numPanel.last() == '.' || emptyNext) && newNum == "."){
                newNum = ""
            }

            if(numPanel == "0" && newNum != "."){
                numPanel = ""
            }

            if(emptyNext){
                numPanel = ""
                emptyNext = false
            }

            if(resultTracker.text.last() == '='){
                resultTracker.text = " "
            }

            resultTextView.text = numPanel + newNum
            operand2 = resultTextView.text.toString()

        }

    }


    private fun compute(op1: Double, op2: Double, operation: String): Double{
        return when(operation){
            "+" -> op1+op2
            "-" -> op1-op2
            "*" -> op1*op2
            "/" -> op1/op2
            else -> 0.0
        }

    }



    fun equalsClick(view: View){

        if(view is Button){



            if(operand2 != "" && operand1 != ""){

                resultTracker.text = operand1.toDouble().toString() + operation + operand2.toDouble().toString() + "="

                resultTextView.text = compute(operand1.toDouble(), operand2.toDouble(), operation).toString()
                operand1 = ""
                operand2 = resultTextView.text.toString()
                emptyNext = true

            }
        }
    }


    fun operationClick(view: View){

        if(view is Button){

            if(operand2 != "" && operand1 != ""){

                val result: Double = this.compute(operand1.toDouble(), operand2.toDouble(), operation)

                operand1 = result.toString()
                operand2 = ""

                resultTextView.text = ""

                operation = view.text.toString()

                resultTracker.text = operand1 + operation


            }
            else if(operand2 != "" && operand1 == ""){

                operation = view.text.toString()

                operand1 = operand2
                operand2 = ""
                resultTextView.text = ""

                resultTracker.text = operand1.toDouble().toString() + operation
            }
            else if(operand2 == "" && operand1 != ""){

                operation = view.text.toString()
                resultTracker.text = operand1.toDouble().toString() + operation
            }


        }
    }

    fun singleNumAct(view: View){

        if(view is Button){

            when(view.text){
                "!" -> {
                    var factorial: Long = 1


                    if(operand2 != ""){
                        if(operand2.toDouble()<0){

                            resultTextView.text = "ERROR"
                            resultTracker.text = " "
                            operand1 = ""
                            operand2 = ""

                        }
                        else{

                            for(i in 1..operand2.toDouble().toInt()){
                                factorial*=i
                            }
                            resultTextView.text = factorial.toString()
                            operand2 = resultTextView.text.toString()
                        }
                        emptyNext=true

                    }


                }
                "√" -> {

                    if(operand2 != ""){
                        if(operand2.toDouble()<0){
                            resultTextView.text = "ERROR"
                            resultTracker.text = " "
                            operand1 = ""
                            operand2 = ""

                        }else{
                            resultTextView.text = sqrt(operand2.toDouble()).toString()
                            operand2 = resultTextView.text.toString()

                        }
                        emptyNext=true
                    }


                }
                "±"-> {
                    if(operand2 != ""){
                        resultTextView.text = (operand2.toDouble()*(-1)).toString()
                        operand2 = resultTextView.text.toString()
                        emptyNext = true
                    }

                }
                "C" -> {
                    if(emptyNext){
                        resultTextView.text = ""
                        operand2 = ""
                        if(resultTracker.text.last() == '='){
                            resultTracker.text = " "
                        }
                        emptyNext = false
                    }else if(operand2 != ""){
                        resultTextView.text = operand2.substring(0, operand2.length - 1)
                        operand2 = resultTextView.text.toString()
                    }else if(operand2 == ""){
                        operand1=""
                        operand2=""
                        operation=""
                        resultTracker.text = " "
                    }
                }
            }
        }
    }
}