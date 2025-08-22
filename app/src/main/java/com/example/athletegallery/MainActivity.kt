package com.example.athletegallery

import android.os.Bundle
import android.view.Display
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.athletegallery.ui.theme.AthleteGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AthleteGalleryTheme(darkTheme = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DisplayLayout(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    )
                }
            }
        }
    }
}

@Composable
fun DisplayImage() {
    Column(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .shadow(2.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.leo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(230.dp)
                .height(290.dp)
                .padding(20.dp)
        )
    }
}

@Composable
fun DisplayInformation(modifier: Modifier) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "Leonel Messi",
            fontSize = 15.sp
        )
        Text(
            text = "38 years old",
            fontSize = 13.sp
        )
    }
}

@Composable
fun DisplayLayout(modifier: Modifier) {
    var currentImage by remember { mutableStateOf(1) }
    
    Column(
        modifier, horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DisplayImage()
        DisplayInformation(modifier = Modifier.fillMaxWidth())
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {currentImage ++},
                contentPadding = PaddingValues(horizontal = 40.dp)
            ) { Text(text = "Previous") }
            Button(
                onClick = {},
                contentPadding = PaddingValues(horizontal = 50.dp)
            ) { Text(text = "Next") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AthleteGalleryTheme {
        DisplayLayout(modifier = Modifier.fillMaxSize())
    }
}