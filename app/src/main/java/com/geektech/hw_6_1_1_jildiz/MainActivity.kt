package com.geektech.hw_6_1_1_jildiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.geektech.hw_6_1_1_jildiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.etFirstActivity.setText(getIntent().getStringExtra("data"))
        binding.btnGoToSecond.setOnClickListener {

            val text = binding.etFirstActivity.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(this, "EditText не может быть пустым", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("text", text)
                launcher.launch(intent)
            }
        }
    }

    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK
            && result.data != null
        ) {
            val text = result.data?.getStringExtra("text")
        }
    }
}
