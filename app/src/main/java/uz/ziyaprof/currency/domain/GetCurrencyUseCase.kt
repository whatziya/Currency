package uz.ziyaprof.currency.domain

import kotlinx.coroutines.flow.map
import uz.ziyaprof.currency.data.repository.CurrencyRepository
import javax.inject.Inject

class GetCurrencyUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val currencyMapper: CurrencyMapper,
) {
    fun getCurrencyFromNetwork() = currencyRepository.getCurrencies().map {
        it.map { cardResDto -> currencyMapper.toUIModel(cardResDto) }
    }
}