package ru.igorcodes.kotlinbasics.jetpackLazyColumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.igorcodes.kotlinbasics.jetpackLazyColumn.ui.theme.KotlinBasicsTheme

class JetpackLazyColumnMainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinBasicsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MyNavigation(modifier: Modifier) {
    val navC = rememberNavController()

    NavHost(navController = navC, startDestination = "JetpackLazyColumnFirstPage") {
        composable("JetpackLazyColumnFirstPage") {
            JetpackLazyColumnFirstPage(navC = navC)
        }

        composable(
            "JetpackLazyColumnSecondPage/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            val countryID = it.arguments?.getInt("id")
            countryID?.let { id -> JetpackLazyColumnSecondPage(navC, id) }
        }
    }
}