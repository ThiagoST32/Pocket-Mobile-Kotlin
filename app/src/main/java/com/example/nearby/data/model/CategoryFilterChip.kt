package com.example.nearby.data.model

import androidx.annotation.DrawableRes
import com.example.nearby.R

enum class CategoryFilterChip(
    val description: String,
    @DrawableRes val icon: Int
) {
    ALIMENTACAO(description = "Alimentação", icon = R.drawable.ic_tools_kitchen_2),
    COMPRAS(description = "Compras", icon = R.drawable.ic_shopping_bag),
    HOSPEDAGEM(description = "Hospedagem", icon = R.drawable.ic_bed),
    SUPERMERCADOR(description = "Supermercado", icon = R.drawable.ic_shopping_cart),
    ENTRETERIMENTO(description = "Cinema", icon = R.drawable.ic_movie),
    FARMACIA(description = "Farmácia", icon = R.drawable.ic_first_aid_kit),
    COMBUSTIVEL(description = "Combustível", icon = R.drawable.ic_gas_station),
    PADARIA(description = "Padaria", icon = R.drawable.ic_bakery);

    companion object{
        fun fromDescription(description: String): CategoryFilterChip?{
            return entries.find { it.description == description }
        }
    }
}