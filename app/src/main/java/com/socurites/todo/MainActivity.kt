package com.socurites.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.socurites.todo.model.ToDoData
import com.socurites.todo.ui.theme.ToDoAppTheme
import com.socurites.todo.vm.ToDoViewModel

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
                    TopLevel()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLevel(viewModel: ToDoViewModel = viewModel()) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ToDoInput(
                text = viewModel.text.observeAsState("").value,
                onTextChange = viewModel.setText,
                onSubmit = viewModel.onSubmit
            )

            LazyColumn {
                items(items = viewModel.toDoList, key = { item ->  item.key }) {todoData ->
                    ToDo(
                        todoData = todoData,
                        onToggle = viewModel.onToggle,
                        onDelete = viewModel.onDelete,
                        onEdit = viewModel.onEdit,
                    )
                }
            }
            ToDoNav()
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoInput(
    text: String,
    onTextChange: (String) -> Unit,
    onSubmit: (String) -> Unit,
) {
    Row(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = {
            onSubmit(text)
        }) {
            Text(text = "입력")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDo(
    todoData: ToDoData,
    onEdit: (key: Int, text: String) -> Unit = {_, _ -> },
    onToggle: (key: Int, checked: Boolean) -> Unit = {_, _ -> },
    onDelete: (key: Int) -> Unit = { },
) {
    var isEditing by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Crossfade(targetState = isEditing, label = "") {
            when(it) {
                false -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(
                            text = todoData.text,
                            modifier = Modifier.weight(1f),
                        )
                        Text(
                            text = "완료",
                        )
                        Checkbox(
                            checked = todoData.done,
                            onCheckedChange = { checked -> onToggle(todoData.key, checked)})
                        Button(onClick = {
                            isEditing = true
                        }) {
                            Text(text = "수정")
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        Button(onClick = {
                            onDelete(todoData.key)
                        }) {
                            Text(text = "삭제")
                        }
                    }
                }
                true -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp),
                    ) {
                        val (newText, setNewText) = remember { mutableStateOf(todoData.text) }
                        OutlinedTextField(
                            value = newText,
                            onValueChange = setNewText,
                            modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(onClick = {
                            onEdit(todoData.key, newText)
                            isEditing = false
                        }) {
                            Text(text = "완료")
                        }
                    }
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

@Preview(showBackground = true)
@Composable
fun ToDoInputPreview() {
    ToDoAppTheme {
        ToDoInput(text = "테스트", onTextChange = {}, onSubmit = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    ToDoAppTheme {
        ToDo(todoData = ToDoData(1, "nice", true))
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToDoAppTheme {
        TopLevel()
    }
}