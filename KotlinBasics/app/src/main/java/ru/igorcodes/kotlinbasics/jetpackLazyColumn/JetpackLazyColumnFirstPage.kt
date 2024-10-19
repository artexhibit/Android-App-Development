package ru.igorcodes.kotlinbasics.jetpackLazyColumn

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.igorcodes.kotlinbasics.jetpackLazyColumn.ui.theme.KotlinBasicsTheme
import ru.igorcodes.kotlinbasics.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetpackLazyColumnFirstPage(navC: NavController?) {
    val countryList = retrieveCountries()
    val myContext = LocalContext.current
    val topBarBehaviour = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(topBarBehaviour.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Countries",
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarColors(
                    containerColor = colorResource(R.color.purple500),
                    titleContentColor = Color.White,
                    scrolledContainerColor = colorResource(R.color.purple200),
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                scrollBehavior = topBarBehaviour
            )
        },
        content = { padding ->
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(
                    count = countryList.count(),
                    itemContent = { index ->
                        val country = countryList[index]

                        Card(
                            onClick = {
                                Toast.makeText(
                                    myContext,
                                    "You selected the ${country.countryName}",
                                    Toast.LENGTH_LONG
                                ).show()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .padding(7.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = colorResource(R.color.purple500)
                            ),
                            shape = RoundedCornerShape(10.dp),
                            elevation = CardDefaults.cardElevation(7.dp),
                            border = BorderStroke(2.dp, Color.Red)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(7.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(country.countryImage),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(RoundedCornerShape(100))
                                            .border(2.dp, Color.Red, RoundedCornerShape(100)),
                                        contentScale = ContentScale.Crop,
                                        alignment = Alignment.Center
                                    )

                                    Column(modifier = Modifier.padding(start = 10.dp)) {
                                        Text(
                                            country.countryName,
                                            fontSize = 20.sp,
                                            color = Color.White
                                        )

                                        Spacer(modifier = Modifier.height(3.dp))

                                        Text(
                                            country.countryDetail,
                                            fontSize = 16.sp,
                                            color = Color.White
                                        )
                                    }
                                }
                                Button(
                                    onClick = {
                                        navC?.navigate("JetpackLazyColumnSecondPage/${country.countryID}")
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.White
                                    ),
                                    border = BorderStroke(2.dp, Color.Red)
                                ) {
                                    Icon(
                                        Icons.AutoMirrored.Rounded.ArrowForward,
                                        contentDescription = "Details",
                                        tint = Color.Red
                                    )
                                }
                            }
                        }
                    }
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LazyColumnFirstPagePreview() {
    KotlinBasicsTheme {
        JetpackLazyColumnFirstPage(null)
    }
}