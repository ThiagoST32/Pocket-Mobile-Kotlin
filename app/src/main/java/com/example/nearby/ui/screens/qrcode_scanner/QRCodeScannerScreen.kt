package com.example.nearby.ui.screens.qrcode_scanner

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import com.example.nearby.MainActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun QRCodeScannerScreen(modifier: Modifier = Modifier, onCompletedScan: (String) -> Unit) {
    var context = LocalContext.current

    var scanOptions = ScanOptions()
        .setDesiredBarcodeFormats((ScanOptions.QR_CODE))
        .setPrompt("Leia o QR Code do Cupom")
        .setCameraId(0)
        .setBeepEnabled(true)
        .setOrientationLocked(false)
        .setBarcodeImageEnabled(true)

    var barcodeLaucher = rememberLauncherForActivityResult(ScanContract()) { result ->
        onCompletedScan(result.contents.orEmpty())
    }

    var cameraPermissionLaucher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                ActivityResultContracts.RequestPermission()
            } else {
                barcodeLaucher.launch(scanOptions)
            }
        }

    fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CAMERA
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        )
            barcodeLaucher.launch(scanOptions)
        else if (shouldShowRequestPermissionRationale(
            context as MainActivity,
            android.Manifest.permission.CAMERA
        )){
            Toast.makeText(context, "Necessário permitir o acesso a camera", Toast.LENGTH_SHORT).show()
        }
        else{
            cameraPermissionLaucher.launch(android.Manifest.permission.CAMERA)
        }
    }

    LaunchedEffect(true) {
        checkCameraPermission()
    }

    Column(modifier = modifier.fillMaxSize()){}
}