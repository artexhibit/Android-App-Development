package ru.igorcodes.kotlinbasics.jetpackCompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.igorcodes.kotlinbasics.R
import ru.igorcodes.kotlinbasics.jetpackCompose.ui.theme.KotlinBasicsTheme

class JetpackComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinBasicsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //MyLayouts(name = "Android", modifier = Modifier.padding(innerPadding))
                    //MyAlignments()
                    //ButtonsExample()
                    //TextFieldsExample(modifier = Modifier.padding(innerPadding))
                    //ImagesExample(modifier = Modifier.padding(innerPadding))
                    //CheckBoxesExample(modifier = Modifier.padding(innerPadding))
                    //RadioButtonsExample(modifier = Modifier.padding(innerPadding))
                    SwitchExample(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SwitchExample(modifier: Modifier = Modifier) {
    val switchState = remember { mutableStateOf(false) }
    val myText = remember { mutableStateOf("The image is visible") }
    val myAlphaValue = remember { mutableStateOf(1F) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(50.dp))

        Switch(
            checked = switchState.value,
            onCheckedChange = {
                switchState.value = it

                if (!switchState.value) {
                    myText.value = "This image is visible"
                    myAlphaValue.value = 1F
                } else {
                    myText.value = "This image is invisible"
                    myAlphaValue.value = 0F
                }
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Green,
                checkedTrackColor = Color.Blue,
                uncheckedThumbColor = Color.Gray,
                uncheckedTrackColor = Color.Yellow
            )
        )

        Spacer(modifier = Modifier.size(30.dp))

        Image(
            painter = painterResource(R.drawable.cat),
            contentDescription = "",
            modifier = Modifier
                .size(300.dp)
                .alpha(myAlphaValue.value),
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.size(30.dp))

        Text(
            text = myText.value,
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.Blue)
                .width(300.dp)
                .padding(vertical = 10.dp)
        )
    }
}

@Composable
fun RadioButtonsExample(modifier: Modifier = Modifier) {
    val myBackgroundColor = remember { mutableStateOf(Color.White) }
    val radioIndex = remember { mutableStateOf(0) }
    val radioTexts = listOf("Red", "Green", "Yellow", "Gray")
    val colorList = listOf(Color.Red, Color.Green, Color.Yellow, Color.Gray)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(myBackgroundColor.value),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column {
            Spacer(modifier = Modifier.size(75.dp))

            radioTexts.forEachIndexed { position, name ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        radioIndex.value = position
                    }
                ) {
                    RadioButton(
                        selected = name == radioTexts[radioIndex.value],
                        onClick = {
                            radioIndex.value = position
                        },
                        colors = RadioButtonDefaults.colors(Color.Blue)
                    )

                    Text(text = name)
                }
            }

            Spacer(modifier = Modifier.size(50.dp))
        }

        Button(onClick = {
            myBackgroundColor.value = colorList[radioIndex.value]
        }) {
            Text(text = "Change Background Color")
        }
    }
}

@Composable
fun CheckBoxesExample(modifier: Modifier = Modifier) {
    val myColor = 0xFFDD028D
    val resultText = remember { mutableStateOf("What is your gender?") }
    val firstCheckbox = remember { mutableStateOf(false) }
    val secondCheckbox = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF027CDD)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(150.dp))

        Text(
            text = resultText.value,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .width(300.dp)
                .background(Color(myColor))
                .padding(vertical = 15.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(50.dp))

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = firstCheckbox.value,
                    onCheckedChange = {
                        firstCheckbox.value = it

                        if (firstCheckbox.value) {
                            resultText.value = "Your gender is Male"
                            secondCheckbox.value = false
                        } else {
                            resultText.value = "What is your gender"
                        }
                    },
                    colors = CheckboxDefaults.colors(Color(myColor))
                )

                Text(
                    text = "Male",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.size(5.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = secondCheckbox.value,
                    onCheckedChange = {
                        secondCheckbox.value = it

                        if (secondCheckbox.value) {
                            resultText.value = "Your gender is Female"
                            firstCheckbox.value = false
                        } else {
                            resultText.value = "What is your gender"
                        }
                    },
                    colors = CheckboxDefaults.colors(Color(myColor))
                )

                Text(
                    text = "Female",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ImagesExample(modifier: Modifier = Modifier) {
    val myButtonBackgroundColor = remember { mutableStateOf(Color.Red) }
    val myButtonText = remember { mutableStateOf("Do your magic") }
    val myButtonTextColor = remember { mutableStateOf(Color.White) }
    val myTextColor = remember { mutableStateOf(Color.Black) }
    val valueOnTextFiled = remember { mutableStateOf("") }
    val userInput = remember { mutableStateOf("Result") }
    val myImage = remember { mutableIntStateOf(R.drawable.cat) }
    var isImageChanged = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(myImage.value),
            contentDescription = "First Image",
            modifier = Modifier.size(300.dp),
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.size(30.dp))

        Button(
            onClick = {
                isImageChanged.value = !isImageChanged.value
                userInput.value = valueOnTextFiled.value
                valueOnTextFiled.value = ""
                myImage.intValue = if (isImageChanged.value) R.drawable.bird else R.drawable.cat
            },
            modifier = Modifier.size(250.dp, 60.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = myButtonBackgroundColor.value),
            border = BorderStroke(3.dp, Color.Black)
        ) {
            Text(
                text = myButtonText.value,
                color = myButtonTextColor.value,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.size(30.dp))

        TextField(
            value = valueOnTextFiled.value,
            onValueChange = {
                valueOnTextFiled.value = it
            },
            label = {
                Text("Enter your name", color = Color.Blue)
            },
            modifier = Modifier.width(300.dp),
            textStyle = TextStyle.Default.copy(fontSize = 25.sp),
            maxLines = 2,
            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            //visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.size(30.dp))

        Text(
            text = userInput.value,
            color = myTextColor.value,
            fontSize = 25.sp,
            modifier = Modifier
                .background(Color.Red)
                .padding(10.dp)
        )
    }
}

@Composable
fun TextFieldsExample(modifier: Modifier = Modifier) {
    val myButtonBackgroundColor = remember { mutableStateOf(Color.Red) }
    val myButtonText = remember { mutableStateOf("Do your magic") }
    val myButtonTextColor = remember { mutableStateOf(Color.White) }
    val myTextColor = remember { mutableStateOf(Color.Black) }
    val valueOnTextFiled = remember { mutableStateOf("") }
    val userInput = remember { mutableStateOf("Result") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                userInput.value = valueOnTextFiled.value
                valueOnTextFiled.value = ""
            },
            modifier = Modifier.size(250.dp, 60.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = myButtonBackgroundColor.value),
            border = BorderStroke(3.dp, Color.Black)
        ) {
            Text(
                text = myButtonText.value,
                color = myButtonTextColor.value,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.size(30.dp))

        TextField(
            value = valueOnTextFiled.value,
            onValueChange = {
                valueOnTextFiled.value = it
            },
            label = {
                Text("Enter your name", color = Color.Blue)
            },
            modifier = Modifier.width(300.dp),
            textStyle = TextStyle.Default.copy(fontSize = 25.sp),
            maxLines = 2,
            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            //visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.size(30.dp))

        Text(
            text = userInput.value,
            color = myTextColor.value,
            fontSize = 25.sp,
            modifier = Modifier
                .background(Color.Red)
                .padding(10.dp)
        )
    }
}

@Composable
fun ButtonsExample() {
    val myButtonBackgroundColor = remember { mutableStateOf(Color.Red) }
    val myButtonText = remember { mutableStateOf("Do your magic") }
    val myButtonTextColor = remember { mutableStateOf(Color.White) }
    val myText = remember { mutableStateOf("Hello World") }
    val myTextColor = remember { mutableStateOf(Color.Black) }
    val buttonStatus = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (buttonStatus.value) {
            Text(
                text = myText.value,
                color = myTextColor.value,
                fontSize = 25.sp,
                modifier = Modifier
                    .background(Color.Red)
                    .padding(10.dp)
            )
        }

        Spacer(modifier = Modifier.size(30.dp))

        Button(
            onClick = {
                if (buttonStatus.value) {
                    myButtonBackgroundColor.value = Color.Black
                    myButtonText.value = "Compose is fun"
                    myButtonTextColor.value = Color.Red
                    myText.value = "Hello Compose!"
                    myTextColor.value = Color.White

                    buttonStatus.value = false
                } else {
                    myButtonBackgroundColor.value = Color.Red
                    myButtonText.value = "Do your magic"
                    myButtonTextColor.value = Color.White
                    myText.value = "Hello World!"
                    myTextColor.value = Color.Black

                    buttonStatus.value = true
                }
            },
            //For a Circle Button
            //modifier = Modifier.size(250.dp),
            //shape = RoundedCornerShape(100),
            modifier = Modifier.size(250.dp, 60.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = myButtonBackgroundColor.value),
            border = BorderStroke(3.dp, Color.Black)
        ) {
            Text(
                text = myButtonText.value,
                color = myButtonTextColor.value,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun MyAlignments() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        //contentAlignment = Alignment.Center for Box Layout
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.Red)
                .padding(start = 20.dp, end = 30.dp)
        )

        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = "Android",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.Red)
                .width(100.dp)
        )

        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = "Kotlin",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.Red)
                .width(100.dp)
        )
    }
}

@Composable
fun MyLayouts(name: String, modifier: Modifier = Modifier) {
    //Layouts
    Column {
        Box(
            modifier = Modifier
                .background(Color.Cyan)
                .height(200.dp)
                .width(200.dp)
        )
        //.fillMaxSize()
        //.size(200.dp)
        {
            Row {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Blue)
                ) {}
                Column {
                    Text(text = "Hello ", modifier = modifier)
                    Text(text = "$name!", modifier = modifier)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinBasicsTheme {
        SwitchExample()
        //RadioButtonsExample()
        //CheckBoxesExample()
        //ImagesExample()
        //TextFieldsExample()
        //ButtonsExample()
        //MyAlignments()
        //MyLayouts(name = "Android")
    }
}