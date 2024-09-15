package uz.ziyaprof.currency.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyResDto(
    val id: Int,
    val Code: String,
    val Ccy: String,
    @SerialName("CcyNm_RU") val ccyNmRU: String,
    @SerialName("CcyNm_UZ") val ccyNmUZ: String,
    @SerialName("CcyNm_UZC") val ccyNmUZC: String,
    @SerialName("CcyNm_EN") val ccyNmEN: String,
    val Nominal: String,
    val Rate: String,
    val Diff: String,
    val Date: String
)