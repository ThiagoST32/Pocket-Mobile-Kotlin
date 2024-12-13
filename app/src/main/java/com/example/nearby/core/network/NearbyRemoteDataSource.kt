package com.example.nearby.core.network

import com.example.nearby.core.network.KtorHttpClient.httpClientAndroid
import com.example.nearby.data.model.Category
import com.example.nearby.data.model.Coupon
import com.example.nearby.data.model.Market
import com.example.nearby.data.model.MarketDetails
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch

object NearbyRemoteDataSource {

    private const val LOCAL_HOST_EMULATOR_BASE_URL = "http://10.0.2.2:3333"

    private const val BASE_URL = LOCAL_HOST_EMULATOR_BASE_URL

    // - busca de categorias
    // - busca de locais (com base em uma categoria)
    // - busca de detalhes de um local (com base em um local especifico)
    // - gerar cupom apartir da leitura do QR Code

    suspend fun getCategories(): Result<List<Category>> =
        try {
            val categories  = httpClientAndroid.get("$BASE_URL/categories").body<List<Category>>()
            Result.success(categories)
        } catch (ex: Exception) {
            Result.failure(ex)
        }

    suspend fun getMarkets(categoryId: String): Result<List<Market>> =
        try{
            val markets = httpClientAndroid.get("$BASE_URL/markets/category/${categoryId}").body<List<Market>>()
            Result.success(markets)
        } catch (ex: Exception){
            Result.failure(ex)
        }

    suspend fun getMarketsDetails(marketId: String): Result<MarketDetails> =
        try{
            val markets = httpClientAndroid.get("$BASE_URL/markets/${marketId}").body<MarketDetails>()
            Result.success(markets)
        } catch (ex: Exception){
            Result.failure(ex)
        }

    suspend fun patchCoupon(marketId: String): Result<Coupon> =
        try {
            val coupon = httpClientAndroid.patch("$BASE_URL/coupons/${marketId}").body<Coupon>()
            Result.success(coupon)
        } catch (ex: Exception){
            Result.failure(ex)
        }

}