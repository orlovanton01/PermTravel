package ru.mobile.permtravel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.mobile.permtravel.ui.theme.PermTravelTheme
import ru.mobile.permtravel.pages.CPageLayout


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            PermTravelTheme {
                CPageLayout()
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    PermTravelTheme {
        CPageLayout()
    }
}