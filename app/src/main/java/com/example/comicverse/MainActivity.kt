package com.example.comicverse

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comicverse.Books_Available.Books

data class Item(
    val title: String,
    val image: Int,
    val height: Int,
    val summary: String,
    val volumes: Int
)

class MainActivity : ComponentActivity() {

    companion object {
        val books: List<Item>
            get() = listOf(
                Item(
                    title = "Death Note",
                    image = R.drawable.death_note,
                    height = 250,
                    summary = "\"Death Note\" follows Light Yagami, a high school student who finds a supernatural notebook that allows him to kill anyone by writing their name in it. Using the alias \"Kira,\" Light aims to rid the world of criminals, but his actions attract the attention of the genius detective L, leading to a tense and strategic battle of wits.",
                    volumes = 12
                ),
                Item(
                    title = "Solo Leveling",
                    image = R.drawable.solo_leveling,
                    height = 250,
                    summary = "The weakest hunter of all mankind finds himself in a quest to become the most powerful hunter.",
                    volumes = 11
                ),
                Item(
                    title = "Demon Slayer",
                    image = R.drawable.demons_slayer,
                    height = 250,
                    summary = "Follows the adventures of Tanjiro Kamado, a young boy who becomes a demon slayer to avenge his family and cure his sister.",
                    volumes = 23
                )
            )

        val coming: List<Item>
            get() = listOf(
                Item(
                    title = "Immortal king",
                    image = R.drawable.the_daily_life_of_the_immortal_king,
                    height = 200,
                    summary = "\"Death Note\" follows Light Yagami, a high school student who finds a supernatural notebook that allows him to kill anyone by writing their name in it. Using the alias \"Kira,\" Light aims to rid the world of criminals, but his actions attract the attention of the genius detective L, leading to a tense and strategic battle of wits.",
                    volumes = 12
                )
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = Color.Black) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "ComicVerse",
                            style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            color = Color.White
                        )
                        Text(
                            text = "Books Available",
                            style = TextStyle(fontSize = 20.sp),
                            modifier = Modifier.padding(vertical = 16.dp),
                            color = Color.White
                        )
                        LazyRow(
                            modifier = Modifier.height(300.dp)
                        ) {
                            items(books) { item ->
                                ClickableImage(
                                    item = item,
                                    onClick = {
                                        navigateToBook(item) // Navigate to Book activity with the whole item
                                    }
                                )
                            }
                        }
                        Text(
                            text = "Coming Soon",
                            style = TextStyle(fontSize = 20.sp),
                            modifier = Modifier.padding(vertical = 16.dp),
                            color = Color.White
                        )
                        LazyRow(
                            modifier = Modifier.height(300.dp)
                        ) {
                            items(coming) { item ->
                                ClickableImage(
                                    item = item,
                                    onClick = {
                                        // Display a toast message for "Coming Soon"
                                        showToast("Coming Soon")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun navigateToBook(item: Item) {
        val intent = Intent(this, Books::class.java)
        intent.putExtra("title", item.title)
        intent.putExtra("image", item.image)
        intent.putExtra("summary", item.summary)
        intent.putExtra("volumes", item.volumes)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    @Composable
    private fun ClickableImage(item: Item, onClick: () -> Unit) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(4.dp)
                .clickable(onClick = onClick)
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = item.title,
                modifier = Modifier
                    .height(item.height.dp)
                    .width(150.dp)
            )
            Text(
                text = item.title,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 4.dp),
                color = Color.White
            )
        }
    }
}
