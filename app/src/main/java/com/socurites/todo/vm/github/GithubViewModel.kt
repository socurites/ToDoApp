package com.socurites.todo.vm.github

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socurites.todo.model.github.GitRepository
import com.socurites.todo.service.github.GithubService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val githubService: GithubService
): ViewModel() {
    private val repos = mutableStateListOf<GitRepository>()

    fun getRepositories() {
        repos.clear()
        viewModelScope.launch {
            val result = githubService.listRepositories(user = "socurites")
            repos.addAll(result)
        }
    }
}