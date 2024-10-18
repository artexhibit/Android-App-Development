package ru.igorcodes.kotlinbasics.jetpackNavigationOne
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.igorcodes.kotlinbasics.R
import ru.igorcodes.kotlinbasics.jetpackNavigationOne.ui.theme.KotlinBasicsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondPage(navController: NavController?, name: String? = "", age: Int? = 0) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Second Page",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarColors(
                    containerColor = colorResource(R.color.purple500),
                    scrolledContainerColor = colorResource(R.color.purple500),
                    navigationIconContentColor = Color.White,
                    titleContentColor = colorResource(R.color.purple500),
                    actionIconContentColor = colorResource(R.color.purple500)
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController?.popBackStack()
                        }
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Name: $name", color = Color.Black, fontSize = 20.sp)

                Spacer(modifier = Modifier.size(10.dp))

                Text("Age: $age", color = Color.Black, fontSize = 20.sp)
            }
        }
    )
}

@Preview(showBackground = true, device = "id:pixel_9", showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun SecondPagePreview() {
    KotlinBasicsTheme {
        SecondPage(null)
    }
}