package com.example.nearby.ui.component.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.example.nearby.R
import com.example.nearby.data.model.mock.mockUserLocation
import com.example.nearby.ui.screens.home.HomeUiState
import com.example.nearby.ui.screens.utils.findNorthWestPoint
import com.example.nearby.ui.screens.utils.findSouthWestPoint
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import kotlin.collections.orEmpty
import kotlin.math.roundToInt

@Composable
fun NearbyGoogleMap(modifier: Modifier = Modifier, uiState: HomeUiState) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(mockUserLocation, 13f)
    }
    val uiSetting by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = uiSetting
    ) {
        context.getDrawable(R.drawable.img_pin)?.let {
            if (!uiState.markets.isNullOrEmpty()) {
                uiState.marketsLocations?.toImmutableList()
                    ?.forEachIndexed { index, location ->
                        Marker(
                            state = MarkerState(position = location),
                            icon = BitmapDescriptorFactory.fromBitmap(
                                it.toBitmap(
                                    width = density.run { 36.dp.toPx() }
                                        .roundToInt(),
                                    height = density.run { 36.dp.toPx() }
                                        .roundToInt()
                                )
                            ),
                            title = uiState.markets[index].name
                        )
                    }.also {
                        coroutineScope.launch {
                            val allMarks =
                                uiState.marketsLocations?.plus(mockUserLocation)
                            val southwestPoint =
                                findSouthWestPoint(allMarks.orEmpty())
                            val northeastPoint =
                                findNorthWestPoint(allMarks.orEmpty())

                            val centerPointerLatitude =
                                (southwestPoint.latitude + northeastPoint.latitude) / 2
                            val centerPointerLongitude =
                                (southwestPoint.longitude + southwestPoint.longitude) / 2

                            val cameraUpdate =
                                CameraUpdateFactory.newCameraPosition(
                                    CameraPosition(
                                        LatLng(
                                            centerPointerLatitude,
                                            centerPointerLongitude
                                        ),
                                        13f, 0f, 0f
                                    )
                                )
                            delay(200)
                            cameraPositionState.animate(
                                cameraUpdate,
                                durationMs = 500
                            )
                        }
                    }
            }
        }
    }

}

