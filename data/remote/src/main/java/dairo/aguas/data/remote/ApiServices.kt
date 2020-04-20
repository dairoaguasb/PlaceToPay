package dairo.aguas.data.remote

import dairo.aguas.data.model.transaction.TransactionBody
import dairo.aguas.data.model.transaction.TransactionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Dairo Aguas B on 2019-11-21.
 */
interface ApiServices {

    @POST("gateway/process")
    suspend fun processTransaction(@Body transactionBody: TransactionBody): Response<TransactionResponse>

}