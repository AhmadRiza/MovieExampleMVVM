package com.github.ahmadriza.movie.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

data class Crypto(
    @SerializedName("CoinInfo") val coin: Coin,
    @SerializedName("RAW") val rawData: RawData
) {

    fun asEntity() = CryptoEntity(
        id = coin.id.toInt(),
        name = coin.name,
        fullName = coin.fullName,
        price = rawData.idr.price,
        change = rawData.idr.change,
        changePercent = rawData.idr.changePercent,
        subsKey = "${rawData.idr.type}~${rawData.idr.market}~${rawData.idr.from}~${rawData.idr.to}"
    )

}

data class Coin(
    @SerializedName("Id") val id: String,
    @SerializedName("Name") val name: String,
    @SerializedName("FullName") val fullName: String
)

data class RawData(
    @SerializedName("IDR") val idr: IDR
)

data class IDR(
    @SerializedName("PRICE") var price: Double,
    @SerializedName("MARKET") val market: String,
    @SerializedName("FROMSYMBOL") val from: String,
    @SerializedName("TOSYMBOL") val to: String,
    @SerializedName("TYPE") val type: String,
    @SerializedName("CHANGEHOUR") val change: Float,
    @SerializedName("CHANGEPCTHOUR") val changePercent: Float
)


@Entity(indices = [Index(value = ["name"], unique = true)])
data class CryptoEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    val fullName: String,
    var price: Double,
    val subsKey: String,
    var change: Float,
    var changePercent: Float,
    var lastEdit: Date = Date()
)

data class SocketCryptoMessage(
    @SerializedName("PRICE") var price: Double,
    @SerializedName("MARKET") val market: String,
    @SerializedName("FROMSYMBOL") val from: String,
    @SerializedName("TOSYMBOL") val to: String,
    @SerializedName("TYPE") val type: String
)

data class SubscribeSocket(val action: String, val subs: List<String>)