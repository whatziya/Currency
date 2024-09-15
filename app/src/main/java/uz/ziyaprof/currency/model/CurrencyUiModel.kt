package uz.ziyaprof.currency.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrencyUiModel(
    val id: Int,
    val code: String,
    val ccy: String,
    val ccyNmRU: String,
    val ccyNmUZ: String,
    val ccyNmUZC: String,
    val ccyNmEN: String,
    val nominal: String,
    val rate: String,
    val diff: String,
    val date: String
) : Parcelable