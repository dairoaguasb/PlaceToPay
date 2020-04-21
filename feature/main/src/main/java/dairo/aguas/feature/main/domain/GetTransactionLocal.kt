package dairo.aguas.feature.main.domain

import dairo.aguas.data.repository.transaction.TransactionRepository

/**
 * Created by Dairo Aguas B on 20/04/2020.
 */
class GetTransactionLocal(private val transactionRepository: TransactionRepository) {

    suspend fun execute(internalReference: Int) =
        transactionRepository.getTransactionByInternalReference(internalReference)
}