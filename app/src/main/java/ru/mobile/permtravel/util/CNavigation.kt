package ru.mobile.permtravel.util

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.mobile.permtravel.R

@Composable
fun CBottomNavigationBar(navController: NavHostController) {
    NavigationBar  {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(navItem.image),
                        contentDescription = navItem.title
                    )
                },
                label = {
                    Text(text = navItem.title)
                }
            )
        }
    }
}

object NavBarItems {
    val BarItems = listOf(
        CBarItem(
            title = "Авторы",
            image = R.drawable.baseline_people_24,
            route = "authors"
        ),
        CBarItem(
            title = "Места",
            image = R.drawable.baseline_place_24,
            route = "places"
        ),
        CBarItem(
            title = "Карта",
            image = R.drawable.baseline_map_24,
            route = "map"
        )
    )
}

data class CBarItem(
    val title: String,
    val image: Int,
    val route: String
)