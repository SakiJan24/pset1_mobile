package com.example.nav.navigation

import android.widget.Toast
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.nav.data.CharacterInfo
import com.example.nav.ui.CharacterViewModel
import com.example.nav.ui.screens.DetailsScreen
import com.example.nav.ui.screens.HomeScreen
import com.example.nav.ui.screens.ListScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute : NavKey

@Serializable
data object ListRoute : NavKey

@Serializable
data class DetailsRoute(val usr: CharacterInfo) : NavKey

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    characterViewModel: CharacterViewModel
) {
    val context = LocalContext.current
    val backstack = rememberNavBackStack(HomeRoute)
    NavDisplay(
        modifier = modifier,
        backStack = backstack,
        entryDecorators =
            listOf(
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
        onBack = { backstack.removeLastOrNull() },
        transitionSpec = {
            slideInHorizontally(initialOffsetX = { it }) togetherWith slideOutHorizontally(
                targetOffsetX = { -it })
        },
        predictivePopTransitionSpec = {
            slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(
                targetOffsetX = { it })
        },
        popTransitionSpec = {
            slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(
                targetOffsetX = { it })
        },
        entryProvider = { key ->
            when (key) {
                is HomeRoute -> NavEntry(key) {
                    HomeScreen(onNavigate = {
                        Toast.makeText(context, "Navigating to List", Toast.LENGTH_SHORT).show()
                        backstack.add(ListRoute)
                    })
                }

                is ListRoute -> NavEntry(key) {
                    ListScreen(
                        characterViewModel = characterViewModel,
                        onNavigate = { usr ->
                            backstack.add(DetailsRoute(usr))
                        }
                    )
                }

                is DetailsRoute -> NavEntry(key) {
                    DetailsScreen(usr = key.usr)
                }

                else -> throw RuntimeException("Unknown route: $key")
            }
        }
    )
}
