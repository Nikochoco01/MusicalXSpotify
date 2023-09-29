package com.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapplication.dataSource.SpotifyClient
import com.myapplication.model.accessToken.ApiAccess
import com.myapplication.ui.theme.MusicalXSpotifyTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicalXSpotifyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                    executeCall(this)
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Text(
            modifier = Modifier
                .width(279.dp)
                .height(16.dp),
            text = "Musical X Spotify",
            style = TextStyle(
                fontSize = 48.sp,
                lineHeight = 16.sp,
//                fontFamily = FontFamily(Font(R.font.nanum brush script)),
                fontWeight = FontWeight(400),
                color = Color(0xFFFFB597),
            )
        )

        Column(
            Modifier
                .width(360.dp)
                .height(264.dp)
                .padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(48.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            // Child views.
//            TextField(
//                Modifier
//                    .width(328.dp)
//                    .height(56.dp)
//                    .background(color = Color(0xFF2F2826), shape = RoundedCornerShape(size = 4.dp),
//                value = "mail", onValueChange = "mail"){}
        }

        Row(
            Modifier
                .width(360.dp)
                .height(40.dp)
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(64.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(onClick = { /*TODO*/ }) {
                Modifier
                    .padding(0.dp)
                    .width(124.dp)
                    .height(40.dp)
                    .background(
                        color = Color(0xFFFFB597),
                        shape = RoundedCornerShape(size = 100.dp)
                    );
                Text(
                    text = "Connection",
                    // M3/label/large
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        //fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF581D00),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.1.sp,
                    )
                )
            }

            Button(onClick = { /*TODO*/ }) {
                Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFFA08D86),
                        shape = RoundedCornerShape(size = 100.dp)
                    )
                    .width(124.dp)
                    .height(40.dp)
                    .padding(start = 16.dp, top = 10.dp, end = 24.dp, bottom = 10.dp);
                //Image(
                  //  painter = painterResource(id = R.drawable.icon),
                   // contentDescription = "image description",
                   // contentScale = ContentScale.None
                //);
                Text(
                    text = "Subscribe",
                    // M3/label/large
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        //fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFB597),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.1.sp,
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MusicalXSpotifyTheme {
        Greeting()
    }
}

private fun executeCall(context: Context) {
    val credential: String = ApiAccess().grantType
    val clientId: String = ApiAccess().clientId
    val clientSecret: String = ApiAccess().clientSecret
    GlobalScope.launch(Dispatchers.Main) {
        try {
            val response = SpotifyClient.apiServiceAccessToken.getAccessToken(credential, clientId, clientSecret)

            if (response.isSuccessful && response.body() != null) {
                val content = response.body()
                Log.d("Resp token", content?.accessToken.toString())
                Log.d("Resp token type", content?.tokenType.toString())
                Log.d("Resp token expires", content?.expiresIn.toString())
            } else {
                Log.e("Error resp message", response.message())
                Log.e("Error resp code", response.code().toString())
                Toast.makeText(
                    context,
                    "Error Occurred: ${response.message()}",
                    Toast.LENGTH_LONG
                ).show()
            }

        } catch (e: Exception) {
            Log.e("Error exception", "error: ${e.message}")
            Toast.makeText(
                context,
                "Error Occurred: ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}