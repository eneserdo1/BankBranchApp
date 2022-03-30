package com.app.bankbranchapp.presentation.models

import com.google.gson.annotations.SerializedName

class BankListResponse : ArrayList<BankListResponseItem>()

data class BankListResponseItem(
    @field:SerializedName("ID") val iD: Int = 0,
    @field:SerializedName("dc_ADRES") val dc_ADRES: String = "",
    @field:SerializedName("dc_ADRES_ADI") val dc_ADRES_ADI: String= "",
    @field:SerializedName("dc_BANKA_SUBE") val dc_BANKA_SUBE: String= "",
    @field:SerializedName("dc_BANKA_TIPI") val dc_BANKA_TIPI: String= "",
    @field:SerializedName("dc_BANK_KODU") val dc_BANK_KODU: String= "",
    @field:SerializedName("dc_BOLGE_KOORDINATORLUGU") val dc_BOLGE_KOORDINATORLUGU: String= "",
    @field:SerializedName("dc_EN_YAKIM_ATM") val dc_EN_YAKIM_ATM: String= "",
    @field:SerializedName("dc_ILCE") val dc_ILCE: String= "",
    @field:SerializedName("dc_ON_OFF_LINE") val dc_ON_OFF_LINE: String= "",
    @field:SerializedName("dc_ON_OFF_SITE") val dc_ON_OFF_SITE: String= "",
    @field:SerializedName("dc_POSTA_KODU") val dc_POSTA_KODU: String= "",
    @field:SerializedName("dc_SEHIR")  val dc_SEHIR: String= ""
)