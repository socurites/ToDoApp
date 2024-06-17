package com.socurites.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.socurites.todo.screen.TodoScreen
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
                    ToDoNav()
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoNav(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController, "ToDo", modifier = modifier) {
        composable("ToDo") {
            Scaffold {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    TodoScreen()

                    Column {
                        Text("ToDo")
                        Button(onClick = {
                            navController.navigate("Git")
                        }) {
                            Text("Git")
                        }
                    }
                }
            }

        }

        composable("Git") {
            Column {
                Column {
                    Button(onClick = {
                        navController.navigate("ToDo")
                    }) {
                        Text("ToDo")
                    }
                }
            }

        }
        composable("Argument/{productId}") { backStrackEntry ->
            val productId = backStrackEntry.arguments?.getString("productId")
            Text("productId: $productId")
        }
    }
}