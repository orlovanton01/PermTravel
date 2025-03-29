package ru.mobile.permtravel.pages.pagemap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.utsman.osmandcompose.Marker

import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import org.osmdroid.util.GeoPoint
import ru.mobile.permtravel.R
import ru.mobile.permtravel.model.CPlace
import ru.mobile.permtravel.pages.pageplaces.CViewModelPagePlaces
import ru.mobile.permtravel.util.CBarItem

@Composable
fun CPageMap(navController: NavController, modifier : Modifier = Modifier) {

    val viewModel : CViewModelPageMap = viewModel()
    val places by viewModel.places.collectAsState()

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ){
        // define camera state
        val cameraState = rememberCameraState {
            geoPoint = GeoPoint(59.0, 58.0)
            zoom = 8.0 // optional, default is 5.0
            speed = 200
        }



        OpenStreetMap(
            modifier = Modifier.fillMaxSize(),
            cameraState = cameraState
        )
        {

            places.forEach{ place ->
                if (!(place.latitude == 0.0 && place.longitude==0.0)){
                    Marker(
                        state = rememberMarkerState(
                            geoPoint = GeoPoint(place.latitude, place.longitude)), // add marker state
                        title = place.name,
                        snippet = String.format("%.4fº, %.4fº", place.latitude, place.longitude)
                    ){
                        Column(
                            modifier = Modifier
                                .wrapContentSize()
                                .background(color = Color.Gray, shape = RoundedCornerShape(7.dp))
                                .padding(10.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(text = it.title)
                            Text(text = it.snippet)
                            Button(onClick = {
                                navController.navigate("markerinfo/${place.id}")
                            }) { Text("Подробнее") }
                        }
                    }
                }
            }
        }
    }
}