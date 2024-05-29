package com.example.mbti_testapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.result)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val results = intent.getIntegerArrayListExtra("results") ?: arrayListOf()

        // 선택된 값에 따른 결과 알파벳 할당
        val resultTypes = listOf(
            listOf("E", "I"),
            listOf("N", "S"),
            listOf("T", "F"),
            listOf("J", "P")
        )

        var resultString = ""
        for (i in results.indices) {
            // 선택된 값(1 또는 2)에 따른 알파벳을 결과 문자열에 추가
            resultString += resultTypes[i][results[i] - 1]
        }

        // 결과 TextView 업데이트
        val tvResValue: TextView = findViewById(R.id.tv_resValue)
        tvResValue.text = resultString

        // 알파벳 결과에 따른 이미지 설정을 위한 맵 정의
        val resultImageMap = mapOf(
            "infp" to R.drawable.ic_infp,
            "infj" to R.drawable.ic_infj,
            "intp" to R.drawable.ic_intp,
            "intj" to R.drawable.ic_intj,
            "isfp" to R.drawable.ic_isfp,
            "isfj" to R.drawable.ic_isfj,
            "istp" to R.drawable.ic_istp,
            "istj" to R.drawable.ic_istj,
            "enfp" to R.drawable.ic_enfp,
            "enfj" to R.drawable.ic_enfj,
            "entp" to R.drawable.ic_entp,
            "entj" to R.drawable.ic_entj,
            "esfp" to R.drawable.ic_esfp,
            "esfj" to R.drawable.ic_esfj,
            "estp" to R.drawable.ic_estp,
            "estj" to R.drawable.ic_estj
        )

        // 소문자로 변환한 결과 문자열을 사용하여 이미지 리소스 가져오기
        val imageResource = resultImageMap[resultString.lowercase(Locale.ROOT)] ?: R.drawable.ic_launcher_foreground

        // ImageView에 이미지 리소스 설정
        val ivResImg: ImageView = findViewById(R.id.iv_resImg)
        ivResImg.setImageResource(imageResource)

        val btnRetry: Button = findViewById(R.id.btn_res_retry)
        btnRetry.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}