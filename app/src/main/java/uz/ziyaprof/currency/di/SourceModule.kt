package uz.ziyaprof.currency.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.ziyaprof.currency.data.api.CurrencyService
import uz.ziyaprof.currency.data.source.CurrencyRemoteDataSource
import uz.ziyaprof.currency.data.source.CurrencyRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
object SourceModule {
    @Provides
    fun provideCurrencyRemoteDataSource(
        currencyService: CurrencyService
    ): CurrencyRemoteDataSource = CurrencyRemoteDataSourceImpl(currencyService)
}