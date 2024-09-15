package uz.ziyaprof.currency.data.source

import kotlinx.coroutines.flow.Flow
import uz.ziyaprof.currency.data.dto.CurrencyResDto

interface CurrencyRemoteDataSource {
    fun getCurrencies(): Flow<List<CurrencyResDto>>
}