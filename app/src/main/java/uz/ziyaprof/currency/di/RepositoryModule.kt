package uz.ziyaprof.currency.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.ziyaprof.currency.data.repository.CurrencyRepository
import uz.ziyaprof.currency.data.repository.CurrencyRepositoryImpl
import uz.ziyaprof.currency.data.source.CurrencyRemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideCurrencyRepository(
        currencyRemoteDataSource: CurrencyRemoteDataSource
    ): CurrencyRepository = CurrencyRepositoryImpl(currencyRemoteDataSource)

}