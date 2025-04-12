package ru.mobile.permtravel.pages.pagemap

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.utsman.osmandcompose.rememberCameraState
import org.osmdroid.util.GeoPoint


import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Looper
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationCallback
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.rememberMarkerState
import ru.mobile.permtravel.R
import java.util.Locale


private var locationCallback: LocationCallback? = null
var fusedLocationClient: FusedLocationProviderClient? = null

@Composable
fun CPageMap(navController: NavController, modifier : Modifier = Modifier) {

    val viewModel : CViewModelPageMap = viewModel()
    val places by viewModel.places.collectAsState()

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ){
        // Определение стартового положения камеры
        val cameraState = rememberCameraState {
            geoPoint = GeoPoint(59.0, 58.0)
            zoom = 8.0 // optional, default is 5.0
            speed = 200
        }

        val launcherMultiplePermissions = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissionsMap ->
            val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
            if (areGranted) {
                startLocationUpdates()
                Log.w("GEO_PERM", "Permission Granted")
            } else {
                Log.w("GEO_PERM", "Permission Denied")
            }
        }


        val context = LocalContext.current

        var geopoint by remember { mutableStateOf(GeoPoint(0.toDouble(), 0.toDouble())) }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(LocalContext.current)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                for (lo in p0.locations) {
                    // Обновляем позицию пользователя
                    geopoint = GeoPoint(lo.latitude, lo.longitude)
                }
            }
        }

        val permissions = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        // Запуск просьбы разрешения на геолокацию
        SideEffect {
            launcherMultiplePermissions.launch(permissions)
        }

        // При наличии разрешения начинает обновлять
        if (permissions.all {
                ContextCompat.checkSelfPermission(
                    context,
                    it
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            // Get the location
            startLocationUpdates()
        }



        // Объект - карта
        OpenStreetMap(
            modifier = Modifier.fillMaxSize(),
            cameraState = cameraState
        )
        {
            // Перебор мест для добавления маркеров
            places.forEach{ place ->
                // Добавляем маркер
                Marker(
                    // Берем информацию из записи
                    state = rememberMarkerState(
                        geoPoint = GeoPoint(place.latitude, place.longitude)), // add marker state

                title = place.name,
                    snippet = String.format(Locale.ENGLISH,"%.4fº, %.4fº", place.latitude, place.longitude)
                ){
                    // Добавляем наименование, координаты и кнопку для открытия описания
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
                            navController.navigate("placedescription/${place.id}")
                        }) { Text("Подробнее") }
                    }
                }
            }


            // Добавляем маркер текущего местоположения
            key(geopoint){
                Marker(
                    // Берем информацию из записи
                    state = rememberMarkerState(geoPoint = geopoint),
                    title = "Ваше местоположение",
                    snippet = String.format(Locale.ENGLISH,"%.4fº, %.4fº", geopoint.latitude, geopoint.longitude),
                    icon = context.getDrawable(R.drawable.baseline_place_24)
                ){
                        // Добавляем наименование и координаты
                        Column(
                            modifier = Modifier
                                .wrapContentSize()
                                .background(color = Color.Gray, shape = RoundedCornerShape(7.dp))
                                .padding(10.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = it.title)
                            Text(text = it.snippet)
                        }

                }

            }

        }
    }
}

@SuppressLint("MissingPermission")
private fun startLocationUpdates() {
    locationCallback?.let {
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,10000).build()
        fusedLocationClient?.requestLocationUpdates(
            locationRequest,
            it,
            Looper.getMainLooper()
        )
    }
}
