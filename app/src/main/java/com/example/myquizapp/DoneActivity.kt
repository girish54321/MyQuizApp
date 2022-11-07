package com.example.myquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myquizapp.databinding.ActivityDoneBinding
import com.example.myquizapp.modal.DoneDataClass
import com.example.myquizapp.modal.QuestionsBase
import com.example.myquizapp.modal.Results

class DoneActivity : AppCompatActivity() {
    private var binding: ActivityDoneBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoneBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpLotteView()

        val data: DoneDataClass? = intent.getSerializableExtra("DONE") as DoneDataClass
        if(data != null) {
           binding?.finalScore?.text = data.finalScoreText
            binding?.passOrFailed?.text = if(data.finalScore > 6) "Passed" else "Failed"
        }
    }

    private fun setUpLotteView(){
        binding?.lottieView?.setAnimation(R.raw.faild)
        binding?.lottieView?.loop(true)
        binding?.lottieView?.playAnimation()
    }
}
