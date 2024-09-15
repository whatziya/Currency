package uz.ziyaprof.currency.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.ziyaprof.currency.data.repository.CurrencyRepository
import uz.ziyaprof.currency.domain.CurrencyMapper
import uz.ziyaprof.currency.domain.GetCurrencyUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetCardsUseCase(
        currencyRepository: CurrencyRepository,
        currenciesMapper: CurrencyMapper
    ) = GetCurrencyUseCase(currencyRepository, currenciesMapper)
}