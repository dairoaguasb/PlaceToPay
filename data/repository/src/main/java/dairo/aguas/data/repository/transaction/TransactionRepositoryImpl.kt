package dairo.aguas.data.repository.transaction

import dairo.aguas.data.model.transaction.TransactionBody
import dairo.aguas.data.remote.transaction.TransactionDatasource

/**
 * Created by Dairo Aguas B on 20/04/2020.
 */
class TransactionRepositoryImpl(
    private val transactionDatasource: TransactionDatasource
) : TransactionRepository {

    override suspend fun processTransaction(transactionBody: TransactionBody) =
        transactionDatasource.processTransaction(transactionBody)
}