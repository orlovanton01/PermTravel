package ru.mobile.permtravel.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.utsman.osmandcompose.Marker

import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import org.osmdroid.util.GeoPoint

@Composable
fun CPageMap(modifier : Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ){
        // define camera state
        val cameraState = rememberCameraState {
            geoPoint = GeoPoint(58.008252, 56.187958)
            zoom = 20.0 // optional, default is 5.0

        }

        val markerStatePSU = rememberMarkerState(
            geoPoint = GeoPoint(58.008252, 56.187958)
        )

        OpenStreetMap(
            modifier = Modifier.fillMaxSize(),
            cameraState = cameraState
        )
        {
            Marker(
                state = markerStatePSU, // add marker state
                title = "TEST",
                snippet = "Snippet"
            ){
                Column(
                    modifier = Modifier
                        .size(100.dp)
                        .background(color = Color.Gray, shape = RoundedCornerShape(7.dp)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = it.title)
                    Text(text = it.snippet)
                }
            }
        }
    }
}