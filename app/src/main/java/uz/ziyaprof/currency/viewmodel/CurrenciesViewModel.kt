package uz.ziyaprof.currency.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.ziyaprof.currency.domain.GetCurrencyUseCase
import uz.ziyaprof.currency.model.CurrencyUiModel
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrencyUseCase
) : ViewModel() {

    private val _currencies = MutableLiveData<List<CurrencyUiModel>>()
    val currencies: LiveData<List<CurrencyUiModel>> get() = _currencies

    fun getCurrency() {
        viewModelScope.launch {
            getCurrenciesUseCase.getCurrencyFromNetwork().onEach { currencyList ->
                _currencies.value = currencyList
            }.launchIn(this)
        }
    }
}