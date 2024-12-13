package com.example.nearby.ui.screens.market_details

import com.example.nearby.data.model.Rule

data class MarketDetailUiState(
    val rules: List<Rule>? = null,
    val coupon: String? = null
    )
