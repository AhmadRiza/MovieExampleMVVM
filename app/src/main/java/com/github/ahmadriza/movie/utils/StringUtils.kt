package com.github.ahmadriza.movie.utils

import okhttp3.internal.toLongOrDefault
import java.text.NumberFormat
import java.util.*


fun String.formatCurrency(): String {

    val localeID = Locale("in", "ID")
    val formatRupiah = NumberFormat.getCurrencyInstance(localeID)

    formatRupiah.maximumFractionDigits = 0
    formatRupiah.minimumFractionDigits = 0

    return formatRupiah.format(this.toDoubleOrNull() ?: 0.0).replace("Rp","")
}


fun String.clearCurrency() = replace("Rp.", "").replace(".", "").toLongOrDefault(0)

const val DOT = "•"