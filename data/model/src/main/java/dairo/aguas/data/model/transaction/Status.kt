package dairo.aguas.data.model.transaction

import com.squareup.moshi.Json

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
data class Status(
    @Json(name = "status") val status: String,
    @Json(name = "reason") val reason: String,
    @Json(name = "message") val message: String,
    @Json(name = "date") val date: String
)