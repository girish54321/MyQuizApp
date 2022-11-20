package com.example.myquizapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myquizapp.R
import com.example.myquizapp.databinding.ActivityDoneBinding
import com.example.myquizapp.modal.DoneDataClass

class DoneActivity : AppCompatActivity() {
    private var binding: ActivityDoneBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoneBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.doneQuizToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.doneQuizToolbar?.setNavigationOnClickListener {
            onBackPressed()
        }

        val data: DoneDataClass? = intent.getSerializableExtra("DONE") as DoneDataClass
        if(data != null) {
           binding?.finalScore?.text = data.finalScoreText
            val passed = data.finalScore > 6
            binding?.passOrFailed?.text = if(passed)"Passed" else "Failed"
            setUpLotteView(passed)
        }
    }

    private fun setUpLotteView(passed: Boolean){
        binding?.lottieView?.setAnimation(R.raw.faild)
        binding?.lottieView?.loop(true)
        binding?.lottieView?.playAnimation()
    }
}
