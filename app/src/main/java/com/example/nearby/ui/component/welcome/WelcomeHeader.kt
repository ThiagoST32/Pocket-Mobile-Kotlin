package com.example.nearby.ui.component.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nearby.R
import com.example.nearby.ui.theme.Typography

@Composable
fun WelcomeScreenHeader(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo_logo_icon),
            contentDescription = "Imagem Logo Background"
        )
        Text(text = "Boas vindas ao Nearby", style = Typography.headlineLarge)
        Text(
            text = "Tenha cupons de vantagem para usar em seus estabelecimentos favoritos.",
            style = Typography.bodyLarge
        )
    }
}