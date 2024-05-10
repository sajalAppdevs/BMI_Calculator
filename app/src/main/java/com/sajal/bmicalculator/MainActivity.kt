package com.sajal.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var editTextHeight:EditText
    private lateinit var editTextWeight:EditText
    private lateinit var btnCalculate:Button
    private lateinit var textViewResult:TextView
    private lateinit var progressBarBMI:ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextHeight=findViewById(R.id.editTextHeight)
        editTextWeight=findViewById(R.id.editTextWeight)
        btnCalculate=findViewById(R.id.btnCalculate)
        textViewResult=findViewById(R.id.textViewResult)
        progressBarBMI=findViewById(R.id.progressBarBMI)

        btnCalculate.setOnClickListener {
            calculateBMI()
        }

    }
    private fun calculateBMI(){
        val height=editTextHeight.text.toString().toDoubleOrNull()
        val weight=editTextWeight.text.toString().toDoubleOrNull()

        if(height!=null && weight!=null){
            val bmi= weight/(height*height)
            displayResult(bmi)
        } else {
            textViewResult.text="please enter valid height and weight"
        }

    }
    private fun displayResult(bmi:Double){
        val resultText=when{
            bmi<18.5->"Underweight"
            bmi<24.9->"Normal weight"
            bmi<29.9->"Overweight"
            else->"Obese"
        }
        val formattedResult="BMI:%.2f\nCategory:%s".format(bmi,resultText)

        //Update ProgressBar with BMI value (assuming a range from 0 to 30)

        val maxProgress= 35 // Set a maximum value for progress (adjust as need)
        val progress=((bmi/35)*maxProgress).toInt()
        progressBarBMI.progress=progress
        textViewResult.text=formattedResult

    }

}
