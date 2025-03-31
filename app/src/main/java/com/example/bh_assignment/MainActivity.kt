package com.example.bh_assignment

import android.os.Bundle
import android.util.Size
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.bh_assignment.domain.ShoeItem
import com.example.bh_assignment.domain.SizeItem
import com.example.bh_assignment.domain.SizeItemNavType
import com.example.bh_assignment.features.detail.DetailsScreen
import com.example.bh_assignment.features.list.ListScreen
import com.example.bh_assignment.ui.theme.BHAssignmentTheme
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
object ListScreenRoute

@Serializable
data class DetailScreenRoute(
    val resId: Int,
    val title: String,
    val price: String,
    val color: Int,
    val foregroundColor: Int,
    val sizes: List<SizeItem>,
    val variants: List<Int>
)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            BHAssignmentTheme {
                val navController = rememberNavController()
                SharedTransitionLayout {
                    NavHost(navController = navController, startDestination = ListScreenRoute) {
                        composable<ListScreenRoute> {
                            ListScreen(animatedVisibilityScope = this) {
                                // Not suitable for real world app
                                navController.navigate(
                                    DetailScreenRoute(
                                        resId = it.resId,
                                        title = it.title,
                                        price = it.price,
                                        color = it.color.toArgb(),
                                        foregroundColor = it.foregroundColor.toArgb(),
                                        sizes = it.sizes,
                                        variants = it.variants
                                    )
                                )
                            }
                        }

                        composable<DetailScreenRoute>(
                            typeMap = mapOf(typeOf<List<SizeItem>>() to SizeItemNavType)
                        ) { backStackEntry ->
                            // Better to fetch from database in real app
                            val shoeItem: ShoeItem =
                                backStackEntry.toRoute<DetailScreenRoute>().let {
                                    ShoeItem(
                                        resId = it.resId,
                                        title = it.title,
                                        price = it.price,
                                        color = Color(it.color),
                                        foregroundColor = Color(it.foregroundColor),
                                        sizes = it.sizes,
                                        variants = it.variants
                                    )
                                }
                            DetailsScreen(
                                shoeItem = shoeItem,
                                animatedVisibilityScope = this,
                                onBackClick = navController::popBackStack,
                            )
                        }
                    }
                }
            }
        }
    }
}
