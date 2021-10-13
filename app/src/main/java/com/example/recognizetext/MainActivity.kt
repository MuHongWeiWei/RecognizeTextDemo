package com.example.recognizetext

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.chinese.ChineseTextRecognizerOptions
import com.google.mlkit.vision.text.japanese.JapaneseTextRecognizerOptions
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val korean = TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())
        val chinese = TextRecognition.getClient(ChineseTextRecognizerOptions.Builder().build())
        val japanese = TextRecognition.getClient(JapaneseTextRecognizerOptions.Builder().build())

        //中文
        getText(chinese, File(cacheDir, "chinese.png"))
        //日文
        getText(japanese, File(cacheDir, "japanese.png"))
        //韓文
        getText(korean, File(cacheDir, "korean.png"))
        //英文
        getText(chinese, File(cacheDir, "english.png"))
    }

    private fun getText(textRecognizer: TextRecognizer, file: File) {
        val bitmap = BitmapFactory.decodeFile(file.path)
        val image = InputImage.fromBitmap(bitmap, 0)
        textRecognizer.process(image)
            .addOnSuccessListener { visionText ->
                Log.e("success", visionText.text)
            }
            .addOnFailureListener { e ->
                Log.e("failure", e.message.toString())
            }
    }
}