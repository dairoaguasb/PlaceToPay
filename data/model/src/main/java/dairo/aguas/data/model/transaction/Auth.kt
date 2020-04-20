package dairo.aguas.data.model.transaction

import com.squareup.moshi.Json

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
data class Auth(
    @Json(name = "login") val login: String = "",
    @Json(name = "tranKey") val tranKey: String = "",
    @Json(name = "nonce") val nonce: String = "",
    @Json(name = "seed") val seed: String = ""
)