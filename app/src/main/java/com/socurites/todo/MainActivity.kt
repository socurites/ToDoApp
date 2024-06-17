package com.socurites.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.socurites.todo.ui.theme.ToDoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    TopLevel()
                }
            }
        }
    }
}



@Composable
fun ToDoNav(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController, "Home", modifier = modifier) {
        composable("Home") {
            Column {
                Text(text = "Home")
                Button(onClick = {
                    navController.navigate("Home") {
                        launchSingleTop = true
                    }
                }) {
                    Text("Home으로 이동")
                }
                Button(onClick = {
                    navController.navigate("Office")
                }) {
                    Text("Office로 이동")
                }
                Button(onClick = {
                    navController.navigate("Argument/product-aaa")
                }) {
                    Text("Product로 이동")
                }
            }
        }
        composable("Office") {
            Column {
                Text(text = "Office")
                Button(onClick = {
                    navController.navigate("Home")
                }) {
                    Text("Home으로 이동")
                }
            }
        }
        composable("Argument/{productId}") { backStrackEntry ->
            val productId = backStrackEntry.arguments?.getString("productId")
            Text("productId: $productId")
        }
    }
}