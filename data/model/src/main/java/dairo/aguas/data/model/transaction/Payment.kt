package dairo.aguas.data.model.transaction

import com.squareup.moshi.Json

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
data class Payment(
    @Json(name = "reference") val reference: String,
    @Json(name = "amount") val amount: Amount
)