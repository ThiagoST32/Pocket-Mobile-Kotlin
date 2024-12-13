package com.example.nearby.ui.screens.market_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nearby.core.network.NearbyRemoteDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MarketDetailsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MarketDetailUiState())
    val uiState: StateFlow<MarketDetailUiState> = _uiState.asStateFlow()

    fun onEvent(event: MarketDetailUiEvent){
        when(event){
            is MarketDetailUiEvent.OnFetchCoupon -> fetchCoupon(qrCodeContent = event.qrCodeContent)
            is MarketDetailUiEvent.OnFetchRules -> fetchRules(marketId = event.marketId)
            MarketDetailUiEvent.OnResetCoupon -> resetCoupon()
        }
    }

    private fun fetchCoupon(qrCodeContent: String){
        viewModelScope.launch{
            NearbyRemoteDataSource.patchCoupon(marketId = qrCodeContent)
                .onSuccess { coupon ->
                    _uiState.update { currentUiState ->
                        currentUiState.copy(
                            coupon = coupon.coupon
                        )
                    }
                }
                .onFailure {
                    _uiState.update { currentUiState ->
                            currentUiState.copy(
                                coupon = ""
                            )
                    }
                }
        }
    }

    private fun fetchRules(marketId: String){
        viewModelScope.launch{
            NearbyRemoteDataSource.getMarketsDetails(marketId = marketId)
                .onSuccess { market ->
                    _uiState.update { currentUiState ->
                        currentUiState.copy(
                            rules = market.rule
                        )
                    }
                }
                .onFailure {
                    _uiState.update { currentUiState ->
                        currentUiState.copy(
                            rules = emptyList()
                        )
                    }
                }
        }
    }

    private fun resetCoupon(){
        _uiState.update { currentUiState ->
            currentUiState.copy(
                coupon = null
            )
        }
    }
}