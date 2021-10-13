# ML Kit 免費圖片文字辨識  Text Recognition

##### ML Kit 免費圖片文字辨識  Text Recognition 可以辨識出中文，這個SDK是免費的，讓你感受到OCR的力量，雖然辨識度沒有到很好但我相信Google團隊會慢慢開發越來越厲害。

---

#### 文章目錄
<ol>
	<li><a href="#a">導入Recognition Library</a></li>
    <li><a href="#b">設定辨識的文字語言</a></li>
    <li><a href="#c">取得要辨識的圖片</a></li>
    <li><a href="#d">辨識圖片後回傳的資料</a></li>
    <li><a href="#e">程式範例</a></li>
	<li><a href="#f">效果展示</a></li>
	<li><a href="#g">Github</a></li>
	<li><a href="#h">相關參考</a></li>
</ol>

---

<a id="a"></a>
#### 1.導入Recognition Library
```Gradle
//中文
implementation 'com.google.mlkit:text-recognition-chinese:16.0.0-beta1'
//日文
implementation 'com.google.mlkit:text-recognition-japanese:16.0.0-beta1'
//韓文
implementation 'com.google.mlkit:text-recognition-korean:16.0.0-beta1'
```

<a id="b"></a>
#### 2.設定辨識的文字語言
```Kotlin
val korean = TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())
val chinese = TextRecognition.getClient(ChineseTextRecognizerOptions.Builder().build())
val japanese = TextRecognition.getClient(JapaneseTextRecognizerOptions.Builder().build())
```

<a id="c"></a>
#### 3.取得要辨識的圖片
##### 圖片在App cache內
<a href="https://badgameshow.com/fly/wp-content/uploads/2021/10/擷取.png"><img src="https://badgameshow.com/fly/wp-content/uploads/2021/10/擷取.png" width="80%"/></a>
##### a.取得Uri
```Kotlin
//取得檔案
val file = File(cacheDir, "chinese.png")
val uri = file.toUri()
//轉成InputImage
val image = InputImage.fromFilePath(this, uri)
```

##### b.取得Bitmap
```Kotlin
//取得檔案
val file = File(cacheDir, "chinese.png")
val bitmap = BitmapFactory.decodeFile(file.path)
//轉成InputImage
val image = InputImage.fromBitmap(bitmap, 0)
```


<a id="d"></a>
#### 4.辨識圖片後回傳的資料
```Kotlin
chinese.process(image)
    .addOnSuccessListener { visionText ->
        Log.e("success", visionText.text)
    }
    .addOnFailureListener { e ->
        Log.e("failure", e.message.toString())
    }
```

<a id="e"></a>
#### 5.程式範例
```Kotlin
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
```
<a id="f"></a>
#### 6.效果展示
<a href="https://badgameshow.com/fly/wp-content/uploads/2021/10/擷取-1.png"><img src="https://badgameshow.com/fly/wp-content/uploads/2021/10/擷取-1.png" width="80%"/></a>


<a id="g"></a>
#### 7.Github
[ML Kit 免費圖片文字辨識  Text Recognition  Github](https://github.com/MuHongWeiWei/RecognizeTextDemo)

<a id="h"></a>
#### 8.相關參考
[https://developers.google.com/ml-kit/vision/text-recognition/v2](https://developers.google.com/ml-kit/vision/text-recognition/v2 "https://developers.google.com/ml-kit/vision/text-recognition/v2")

[https://developers.google.com/ml-kit/vision/text-recognition/v2/languages](https://developers.google.com/ml-kit/vision/text-recognition/v2/languages "https://developers.google.com/ml-kit/vision/text-recognition/v2/languages")

[https://developers.google.com/ml-kit/vision/text-recognition/v2/android](https://developers.google.com/ml-kit/vision/text-recognition/v2/android "https://developers.google.com/ml-kit/vision/text-recognition/v2/android")

