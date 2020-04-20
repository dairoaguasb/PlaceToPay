package dairo.aguas.data.remote.transaction

import com.squareup.moshi.Moshi
import dairo.aguas.data.model.transaction.TransactionBody
import dairo.aguas.data.model.transaction.TransactionResponse
import dairo.aguas.data.model.vo.Result
import dairo.aguas.data.remote.ApiServices
import dairo.aguas.data.remote.retrofit.ErrorHandler
import dairo.aguas.data.model.retrofit.ErrorResponse

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
class TransactionDatasource(private val apiServices: ApiServices, private val moshi: Moshi) {

    suspend fun processTransaction(transactionBody: TransactionBody): Result<TransactionResponse> {
        try {
            apiServices.processTransaction(transactionBody).run {
                return if (isSuccessful && body() != null)
                    Result.Success(body() as TransactionResponse)
                else
                    Result.Failure(
                        Exception(
                            ErrorHandler.parseError<ErrorResponse>(errorBody(), moshi)?.message
                        )
                    )
            }
        } catch (e: Exception) {
            return Result.Failure(e)
        }
    }
}