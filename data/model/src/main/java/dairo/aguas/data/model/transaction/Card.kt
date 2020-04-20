package dairo.aguas.data.model.transaction

import com.squareup.moshi.Json

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
data class Card(
    @Json(name = "expirationYear") val expirationYear: String,
    @Json(name = "number") val number: String,
    @Json(name = "cvv") val cvv: String,
    @Json(name = "expirationMonth") val expirationMonth: String
)