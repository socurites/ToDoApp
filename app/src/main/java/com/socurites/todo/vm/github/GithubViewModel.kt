package com.socurites.todo.vm.github

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socurites.todo.model.github.GitRepos
import com.socurites.todo.service.github.GithubService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val githubService: GithubService
): ViewModel() {
    private val _repos = mutableStateListOf<GitRepos>()
    val repos = _repos

    fun getRepositories() {
        _repos.clear()
        viewModelScope.launch {
            val result = githubService.listRepos(user = "socurites")
            _repos.addAll(result)
        }
    }
}