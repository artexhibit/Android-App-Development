package ru.igorcodes.kotlinbasics.jetpackNavigationOne
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.igorcodes.kotlinbasics.jetpackNavigationOne.ui.theme.KotlinBasicsTheme

class JetpackNavigationOneMainActivity: ComponentActivity() {
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
fun MyNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "JetpackNavigationOneMainPage") {
        composable(route = "JetpackNavigationOneMainPage") {
            MainPage(navController)
        }

        composable(
            route = "JetpackNavigationOneSecondPage/{userName}/{userAge}",
            arguments = listOf(
                navArgument("userName") {
                    type = NavType.StringType
                },
                navArgument("userAge") {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString("userName")
            val age = navBackStackEntry.arguments?.getInt("userAge")

            SecondPage(navController, name, age)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetpackNavigationOneMainActivityPreview() {
    KotlinBasicsTheme {
        MyNavigation()
    }
}