package uz.ziyaprof.currency.data.source

import kotlinx.coroutines.flow.flow
import uz.ziyaprof.currency.data.api.CurrencyService
import javax.inject.Inject

class CurrencyRemoteDataSourceImpl @Inject constructor(
    private val currencyService: CurrencyService
) : CurrencyRemoteDataSource{

    override fun getCurrencies() = flow {
        emit(currencyService.getCurrencies())
    }
}