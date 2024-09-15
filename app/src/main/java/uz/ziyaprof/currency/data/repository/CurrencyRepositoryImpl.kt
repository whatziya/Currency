package uz.ziyaprof.currency.data.repository

import uz.ziyaprof.currency.data.source.CurrencyRemoteDataSource
import javax.inject.Inject
import javax.sql.DataSource

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyRemoteDataSource: CurrencyRemoteDataSource
) : CurrencyRepository {
    override fun getCurrencies() = currencyRemoteDataSource.getCurrencies()
}