
package com.example.gameslist

import MainViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gameslist.ui.theme.GamesListTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Create ViewModel (for production, use 'viewModel()' for proper lifecycle management)
        val viewModel = MainViewModel()

        // Fetch games from the API
        viewModel.getGames()

        setContent {
            // Collect the games state from the ViewModel
            val games = viewModel.games.collectAsState()

            // Theme wrapper
            GamesListTheme {
                Column (modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)){
                    // Display the list of games using LazyColumn
                    LazyColumn {
                        items(games.value) {
                            // Assuming 'title' exists in GameResponse

                            Card(modifier = Modifier.fillMaxWidth()) {
                                Column(modifier = Modifier.padding(16.dp)) {

                                    AsyncImage(model = it.thumbnail, contentDescription =null, modifier = Modifier.fillMaxWidth().padding(10.dp), contentScale = ContentScale.FillWidth)
                                    Text(text = it.title ?: "", fontWeight = FontWeight.Bold)

                                }
                                //Spacer(modifier = Modifier.height(20.dp))
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            }
        }
    }
}
