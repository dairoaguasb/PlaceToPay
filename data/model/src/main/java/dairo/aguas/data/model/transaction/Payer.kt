package dairo.aguas.data.model.transaction

import com.squareup.moshi.Json

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
data class Payer(
    @Json(name = "name") val name: String,
    @Json(name = "mobile") val mobile: String,
    @Json(name = "email") val email: String
)