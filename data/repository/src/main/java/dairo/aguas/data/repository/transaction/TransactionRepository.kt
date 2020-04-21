package dairo.aguas.data.repository.transaction

import dairo.aguas.data.model.transaction.TransactionBody
import dairo.aguas.data.model.transaction.TransactionResponse
import dairo.aguas.data.model.transaction.Transactions
import dairo.aguas.data.model.vo.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dairo Aguas B on 20/04/2020.
 */
interface TransactionRepository {

    suspend fun processTransaction(transactionBody: TransactionBody): Result<TransactionResponse>

    suspend fun setTransactionLocal(transactions: Transactions)

    suspend fun getTransactionByInternalReference(internalReference: Int): Transactions

    fun getTransactionLocalListFlow(): Flow<List<Transactions>>

    suspend fun deleteTransaction(idAuto: Int)
}