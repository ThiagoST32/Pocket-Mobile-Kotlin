package com.example.nearby.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nearby.R
import com.example.nearby.ui.component.button.NearbyButton
import com.example.nearby.ui.component.welcome.WelcomeContent
import com.example.nearby.ui.component.welcome.WelcomeHowItWorksTip
import com.example.nearby.ui.component.welcome.WelcomeScreenHeader
import com.example.nearby.ui.theme.Gray500
import com.example.nearby.ui.theme.RedBase
import com.example.nearby.ui.theme.Typography


@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, onNavigateToHome: () -> Unit) {
    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(horizontal = 40.dp, vertical = 48.dp)
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        WelcomeScreenHeader()
        WelcomeContent()
        NearbyButton(modifier = Modifier.fillMaxWidth().padding(top = 48.dp), text = "Começar", onClick = onNavigateToHome)
    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(onNavigateToHome = {})
}