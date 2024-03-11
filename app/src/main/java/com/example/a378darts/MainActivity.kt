package com.example.a378darts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a378darts.ui.theme._378DartsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "menu") {
                composable("menu") {
                    Menu(onNavigateToCricket = { navController.navigate("cricket") },
                        onNavigateToXOhOne = { is301 -> navController.navigate("x-o-one?is301=${is301}") }
                    )
                }
                composable("cricket") { Cricket(onNavigateToMenu = { navController.navigate("menu") }) }
                composable(
                    "x-o-one",
                    arguments = listOf(navArgument("is301") {
                        type = NavType.BoolType
                    })
                ) { backStackEntry ->
                    XOhOne(
                        onNavigateToMenu = { navController.navigate("menu") },
                        backStackEntry.arguments?.getBoolean("is301") ?: true
                    )
                }
                // Add more destinations similarly.
            }
        }
    }
}

@Composable
fun Menu(
    onNavigateToCricket: () -> Unit,
    onNavigateToXOhOne: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.menu_background),
            contentScale = ContentScale.Crop,
            contentDescription = "Background"
        )

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = com.intuit.sdp.R.dimen._10sdp))
        ) {
            IconButton(modifier = Modifier
                .padding(dimensionResource(id = com.intuit.sdp.R.dimen._5sdp))
                .background(
                    colorResource(id = R.color.purple_200),
                    shape = RoundedCornerShape(
                        dimensionResource(id = com.intuit.sdp.R.dimen._25sdp)
                    )
                )
                .height(dimensionResource(id = com.intuit.sdp.R.dimen._30sdp))
                .width(dimensionResource(id = com.intuit.sdp.R.dimen._30sdp)),
                onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_info),
                    contentDescription = "Info"
                )
            }

            IconButton(modifier = Modifier
                .padding(dimensionResource(id = com.intuit.sdp.R.dimen._5sdp))
                .background(
                    colorResource(id = R.color.purple_200),
                    shape = RoundedCornerShape(
                        dimensionResource(id = com.intuit.sdp.R.dimen._25sdp)
                    )
                )
                .height(dimensionResource(id = com.intuit.sdp.R.dimen._30sdp))
                .width(dimensionResource(id = com.intuit.sdp.R.dimen._30sdp)),
                onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = "Info"
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(start = dimensionResource(id = com.intuit.sdp.R.dimen._20sdp))
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onNavigateToCricket) {
                Text(text = "Cricket")
            }

            Button(onClick = { onNavigateToXOhOne(true) }) {
                Text(text = "301")
            }

            Button(onClick = { onNavigateToXOhOne(false) }) {
                Text(text = "501")
            }
        }
    }
}

@Composable
fun Cricket(
    onNavigateToMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Welcome!",
        modifier = modifier
    )
}

@Composable
fun XOhOne(
    onNavigateToMenu: () -> Unit,
    is301: Boolean,
    modifier: Modifier = Modifier
) {
    val mode = if (is301) "301!" else "501!"
    Text(
        text = "Welcome to $mode",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    _378DartsTheme {
        Menu(onNavigateToCricket = { /*TODO*/ }, onNavigateToXOhOne = { /*TODO*/ })
    }
}