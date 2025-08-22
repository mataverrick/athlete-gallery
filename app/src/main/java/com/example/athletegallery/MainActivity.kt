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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.athletegallery.ui.theme.AthleteGalleryTheme
import java.security.KeyStore

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
fun DisplayImage(currentImage: Int, changeImage: (Int) -> Int) {
    Column(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .shadow(2.dp)
    ) {

        var image = changeImage(currentImage)

        Image(
            painter = painterResource(image),
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
fun DisplayInformation(
    currentInformation: Int,
    changeTitle: (Int) -> String,
    changeInf: (Int) -> String,
    modifier: Modifier
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        var title: String = changeTitle(currentInformation)
        var aditionalInformation: String = changeInf(currentInformation)

        Text(
            text = title,
            fontSize = 15.sp
        )
        Text(
            text = aditionalInformation,
            fontSize = 13.sp
        )
    }
}

@Composable
fun DisplayLayout(modifier: Modifier) {
    var currentInformation by rememberSaveable { mutableStateOf(1) }
    Column(
        modifier, horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        DisplayImage(currentInformation, {
            when (currentInformation) {
                1 -> R.drawable.leo
                2 -> R.drawable.mch
                3 -> R.drawable.usain
                else -> 0
            }
        })

        DisplayInformation(
            modifier = Modifier.fillMaxWidth(),
            currentInformation = currentInformation, changeTitle = {
                when (currentInformation) {
                    1 -> "Leo messi"
                    2 -> "Michael jordan"
                    3 -> "Usain bolt"
                    else -> "dunno"
                }
            },
            changeInf = {
                when (currentInformation) {
                    1 -> "38 years old"
                    2 -> "62 years old"
                    3 -> "39 years old"
                    else -> "dunno"
                }
            })
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { currentInformation-- },
                enabled = if (currentInformation <= 1) false else true,
                contentPadding = PaddingValues(horizontal = 40.dp)
            ) { Text(text = stringResource(R.string.previous)) }
            Button(
                onClick = { currentInformation++ },
                enabled = if (currentInformation < 3) true else false,
                contentPadding = PaddingValues(horizontal = 50.dp)
            ) { Text(text = stringResource(R.string.next)) }
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