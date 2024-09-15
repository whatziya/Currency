package uz.ziyaprof.currency.domain

import uz.ziyaprof.currency.base.BaseMapper
import uz.ziyaprof.currency.data.dto.CurrencyResDto
import uz.ziyaprof.currency.model.CurrencyUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyMapper @Inject constructor() : BaseMapper<CurrencyResDto, CurrencyUiModel> {
    override fun toUIModel(dto: CurrencyResDto) = dto.run {
        CurrencyUiModel(
            id = id,
            code = Code,
            ccy = Ccy,
            ccyNmRU = ccyNmRU,
            ccyNmUZ = ccyNmUZ,
            ccyNmUZC = ccyNmUZC,
            ccyNmEN =ccyNmEN,
            nominal = Nominal,
            rate = Rate,
            diff = Diff,
            date = Date
        )
    }
}