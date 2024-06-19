package com.socurites.todo.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.socurites.todo.ui.theme.ToDoAppTheme
import com.socurites.todo.vm.github.GithubViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GithubReposScreen(viewModel: GithubViewModel = viewModel()) {
    LazyColumn {
        item {
            Button(onClick = {
                viewModel.getRepositories()
            }) {
                Text(text = "List repositories")
            }
        }

        items(items = viewModel.repos, key = { item -> item.id }) {item ->
            Text(text = item.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GithubReposScreenPreview() {
    ToDoAppTheme {
        GithubReposScreen()
    }
}