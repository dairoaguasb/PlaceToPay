package dairo.aguas.data.model.transaction

import com.squareup.moshi.Json

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
data class Amount(
    @Json(name = "total") val total: Int,
    @Json(name = "currency") val currency: String = "COP"
)