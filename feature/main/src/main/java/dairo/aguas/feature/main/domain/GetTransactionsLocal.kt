package dairo.aguas.feature.main.domain

import dairo.aguas.data.repository.transaction.TransactionRepository

/**
 * Created by Dairo Aguas B on 21/04/2020.
 */
class GetTransactionsLocal(private val transactionRepository: TransactionRepository) {

    suspend fun execute() =
        transactionRepository.getTransactionListLocal()
}