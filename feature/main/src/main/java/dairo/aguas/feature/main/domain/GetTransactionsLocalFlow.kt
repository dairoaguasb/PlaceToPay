package dairo.aguas.feature.main.domain

import dairo.aguas.data.repository.transaction.TransactionRepository

/**
 * Created by Dairo Aguas B on 21/04/2020.
 */
class GetTransactionsLocalFlow(private val transactionRepository: TransactionRepository) {

    fun execute() =
        transactionRepository.getTransactionLocalListFlow()
}