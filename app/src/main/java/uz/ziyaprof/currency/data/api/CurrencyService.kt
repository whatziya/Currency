package uz.ziyaprof.currency.data.api

import retrofit2.http.GET
import uz.ziyaprof.currency.data.dto.CurrencyResDto

interface CurrencyService {
    @GET("arkhiv-kursov-valyut/json/")
    suspend fun getCurrencies(): List<CurrencyResDto>
}