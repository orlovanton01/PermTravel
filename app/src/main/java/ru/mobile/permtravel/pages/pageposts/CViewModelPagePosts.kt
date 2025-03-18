package ru.mobile.permtravel.pages.pageposts

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.mobile.permtravel.model.Author
import ru.mobile.permtravel.model.Post
import ru.mobile.permtravel.repositories.CRepositoryAuthors
import ru.mobile.permtravel.repositories.CRepositoryPosts
import java.util.UUID

class CViewModelPagePosts(application: Application) : AndroidViewModel(application) {
    private val repositoryPosts = CRepositoryPosts(application)
    private val repositoryAuthors = CRepositoryAuthors(application)

    fun getPostsByAuthorId(authorId: UUID) : StateFlow<List<Post?>?> {
        return repositoryPosts.getPostsByAuthorId(authorId)
            .stateIn(viewModelScope, SharingStarted.Lazily, null)
    }

    fun getAuthorById(authorId: UUID) : StateFlow<Author?> {
        return repositoryPosts.getAuthorById(authorId)
            .stateIn(viewModelScope, SharingStarted.Lazily, null)

    }

    fun insertPost(post: Post) {
        Log.i("createPost","${post.id}")
        Log.i("createPost","${post.authorId}")
        Log.i("createPost", post.text)
        Log.i("createPost","${post.createdAt}")
        viewModelScope.launch {
            repositoryPosts.insertPost(post)
        }
    }
}