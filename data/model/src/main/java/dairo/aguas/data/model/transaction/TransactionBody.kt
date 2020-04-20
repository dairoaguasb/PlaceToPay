package dairo.aguas.data.model.transaction

import com.squareup.moshi.Json

data class TransactionBody(
    @Json(name = "auth") val auth: Auth,
    @Json(name = "payment") val payment: Payment,
    @Json(name = "instrument") val instrument: Instrument,
    @Json(name = "payer") val payer: Payer
)










