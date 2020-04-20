package dairo.aguas.data.model.transaction

import com.squareup.moshi.Json

data class TransactionResponse(
    @Json(name = "status") val status: Status,
    @Json(name = "internalReference") val internalReference: Int = 0,
    @Json(name = "reference") val reference: String = "",
    @Json(name = "paymentMethod") val paymentMethod: String = "",
    @Json(name = "lastDigits") val lastDigits: String = "",
    @Json(name = "franchiseName") val franchiseName: String = ""
)

