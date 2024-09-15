package uz.ziyaprof.currency.data.repository

import kotlinx.coroutines.flow.Flow
import uz.ziyaprof.currency.data.dto.CurrencyResDto

interface CurrencyRepository {
    fun getCurrencies(): Flow<List<CurrencyResDto>>
}