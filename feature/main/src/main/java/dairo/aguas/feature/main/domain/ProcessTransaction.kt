package dairo.aguas.feature.main.domain

import dairo.aguas.data.model.transaction.TransactionBody
import dairo.aguas.data.repository.transaction.TransactionRepository

/**
 * Created by Dairo Aguas B on 20/04/2020.
 */
class ProcessTransaction(private val transactionRepository: TransactionRepository) {

    suspend fun execute(transactionBody: TransactionBody) =
        transactionRepository.processTransaction(transactionBody)
}