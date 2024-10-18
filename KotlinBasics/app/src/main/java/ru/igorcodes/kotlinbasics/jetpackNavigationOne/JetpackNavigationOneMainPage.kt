package ru.igorcodes.kotlinbasics.jetpackNavigationOne
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.igorcodes.kotlinbasics.R
import ru.igorcodes.kotlinbasics.jetpackNavigationOne.ui.theme.KotlinBasicsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(navController: NavController?) {
    val userName = remember { mutableStateOf("") }
    val userAge = remember { mutableStateOf("") }
    val myContext = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Main Page",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarColors(
                    containerColor = colorResource(R.color.purple500),
                    scrolledContainerColor = colorResource(R.color.purple500),
                    navigationIconContentColor = colorResource(R.color.purple500),
                    titleContentColor = colorResource(R.color.purple500),
                    actionIconContentColor = colorResource(R.color.purple500)
                )
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
                TextField(
                    value = userName.value,
                    onValueChange = {
                        userName.value = it
                    },
                    label = {
                        Text("Enter Your name")
                    },
                    colors = TextFieldDefaults.colors(
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = colorResource(R.color.purple500),
                        unfocusedContainerColor = colorResource(R.color.purple500)
                    ),
                    modifier = Modifier.size(300.dp, 60.dp),
                    textStyle = TextStyle(fontSize = 18.sp, color = Color.White),
                    shape = RoundedCornerShape(5.dp)
                )

                Spacer(modifier = Modifier.size(20.dp))

                TextField(
                    value = userAge.value,
                    onValueChange = {
                        userAge.value = it
                    },
                    label = {
                        Text("Enter Your age")
                    },
                    colors = TextFieldDefaults.colors(
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = colorResource(R.color.purple500),
                        unfocusedContainerColor = colorResource(R.color.purple500)
                    ),
                    modifier = Modifier.size(300.dp, 60.dp),
                    textStyle = TextStyle(fontSize = 18.sp, color = Color.White),
                    shape = RoundedCornerShape(5.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.size(50.dp))

                Button(
                    onClick = {
                        if (userName.value.isEmpty() || userAge.value.isEmpty()) {
                            Toast.makeText(
                                myContext,
                                "Please enter all data",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            try {
                                navController?.navigate("JetpackNavigationOneSecondPage/${userName.value}/${userAge.value}") {
                                    //removes all pages up to MainPage, including MainPage
                                    //popUpTo("JetpackNavigationOneMainPage") { inclusive = true }
                                }
                            } catch (e: IllegalArgumentException) {
                                Toast.makeText(
                                    myContext,
                                    "Please enter valid data",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier.size(200.dp, 60.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(2.dp, color = colorResource(R.color.purple500))
                ) {
                    Text("Send", color = colorResource(R.color.purple500), fontSize = 24.sp)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    KotlinBasicsTheme {
        MainPage(null)
    }
}