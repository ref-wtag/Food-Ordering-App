package com.example.foodorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodorderingapp.databinding.ActivityPayoutBinding

class PayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.placemyorder.setOnClickListener {
            val bottomSheetDialogue = CongratsBottomSheet()
            bottomSheetDialogue.show(supportFragmentManager, "")
        }

        binding.imageButtonPayout.setOnClickListener {
            finish()
        }
    }
}