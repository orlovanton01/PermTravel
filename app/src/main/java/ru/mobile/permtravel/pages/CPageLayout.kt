package ru.mobile.permtravel.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.mobile.permtravel.model.Author
import ru.mobile.permtravel.pages.pageauthors.CPageAuthors
import ru.mobile.permtravel.pages.pageplacedescription.CPagePlaceDescription
import ru.mobile.permtravel.pages.pageplaces.CPagePlaces
import ru.mobile.permtravel.pages.pageposts.CPageCreatePost
import ru.mobile.permtravel.pages.pageposts.CPagePosts
import ru.mobile.permtravel.pages.pageposts.CViewModelPagePosts
import ru.mobile.permtravel.util.CBottomNavigationBar
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CPageLayout() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("От края до края")
                },
                navigationIcon = {
                    if (currentRoute == "placedescription/{id}") {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "back"
                            )
                        }
                    }
                },
            )
        },
        bottomBar = {
            if (currentRoute != "placedescription/{id}" && currentRoute != "posts/{authorId}" && currentRoute != "createPost/{authorId}")
                CBottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        NavHost(
            navController,
            startDestination = "authors",
            modifier = modifier,
        ) {
            composable("authors") { CPageAuthors(navController) }
            composable("places") { CPagePlaces(navController) }
            composable("placedescription/{id}") { navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getString("id") ?: ""
                CPagePlaceDescription(id)
            }
            composable("map") { CPageMap() }
            composable("posts/{authorId}") { navBackStackEntry ->
                val authorId = navBackStackEntry.arguments?.getString("authorId") ?: ""
                CPagePosts(
                    authorId,
                    navController = navController)}
            composable("createPost/{authorId}") { navBackStackEntry ->
                val authorId = navBackStackEntry.arguments?.getString("authorId") ?: ""
                CPageCreatePost(
                    authorId,
                    navController = navController)
            }
        }
    }
}