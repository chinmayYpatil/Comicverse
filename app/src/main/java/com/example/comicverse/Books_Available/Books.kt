package com.example.comicverse.Books_Available

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

class Books : ComponentActivity() {

    private var loading by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val title = intent.getStringExtra("title") ?: "Unknown Title"
        val imageResId = intent.getIntExtra("image", 0)
        val summary = intent.getStringExtra("summary") ?: "No Summary Available"

        setContent {
            Surface(color = Color.Black) {
                BookDetails(
                    title = title,
                    imageResId = imageResId,
                    summary = summary,
                    loading = loading,
                    onReadFullBookClicked = { openFullBookPDF(title) }
                )
            }
        }
    }

    private fun openFullBookPDF(title: String) {
        val pdfUri: Uri? = when (title) {
            "Demon Slayer" -> Uri.parse("https://firebasestorage.googleapis.com/v0/b/comic-verse-8d749.appspot.com/o/Chapter%201%20'Cruelty'.pdf?alt=media&token=ec24d2dc-4984-404c-a598-a7027aa8d342")
            "Solo Leveling" -> Uri.parse("https://firebasestorage.googleapis.com/v0/b/comic-verse-8d749.appspot.com/o/Ch%20001.pdf?alt=media&token=963892e2-adae-4aa7-8763-ac599e45ae47")
            "Death Note" -> Uri.parse("https://firebasestorage.googleapis.com/v0/b/comic-verse-8d749.appspot.com/o/death_note.pdf?alt=media&token=23f10fe0-84b9-4e36-aa14-605d979ef3bf")
            else -> null // or handle default case
        }

        pdfUri?.let {
            loading = true
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(it, "application/pdf")
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(intent)
            loading = false
        }
    }

    @Composable
    private fun BookDetails(
        title: String,
        imageResId: Int,
        summary: String,
        loading: Boolean,
        onReadFullBookClicked: () -> Unit
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                color = Color.White
            )
            if (imageResId != 0) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = title,
                    modifier = Modifier.height(450.dp).fillMaxWidth().padding(8.dp)
                )
            }
            Text(
                text = summary,
                style = TextStyle(fontSize = 16.sp),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = 16.dp),
                color = Color.White
            )
            if (loading) {
                Text(
                    text = "Loading PDF...",
                    style = TextStyle(fontSize = 16.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp),
                    color = Color.White
                )
            } else {
                Button(
                    onClick = { onReadFullBookClicked() },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                ) {
                    Text(text = "Read Full Book")
                }
            }
        }
    }
}
