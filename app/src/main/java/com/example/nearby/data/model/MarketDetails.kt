package com.example.nearby.data.model

data class MarketDetails(
    val id: String,
    val categoryId: String,
    val name: String,
    val description: String,
    val rule: List<Rule>,
    val coupons: Int,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val phone: String,
    val cover: String
)