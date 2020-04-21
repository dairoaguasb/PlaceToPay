package dairo.aguas.data.repository.transaction

import dairo.aguas.data.local.dao.TransactionDao
import dairo.aguas.data.model.transaction.TransactionBody
import dairo.aguas.data.model.transaction.Transactions
import dairo.aguas.data.remote.transaction.TransactionDatasource

/**
 * Created by Dairo Aguas B on 20/04/2020.
 */
class TransactionRepositoryImpl(
    private val transactionDatasource: TransactionDatasource,
    private val transactionDao: TransactionDao
) : TransactionRepository {

    override suspend fun processTransaction(transactionBody: TransactionBody) =
        transactionDatasource.processTransaction(transactionBody)

    override suspend fun setTransactionLocal(transactions: Transactions) {
        transactionDao.insert(transactions)
    }

    override suspend fun getTransactionByInternalReference(internalReference: Int) =
        transactionDao.getTransactionsByInternalReference(internalReference)

    override fun getTransactionLocalListFlow() =
        transactionDao.getTransactionListFlow()

    override suspend fun getTransactionListLocal() =
        transactionDao.getTransactionList()

    override suspend fun deleteTransaction(idAuto: Int) {
        transactionDao.deleteTransaction(idAuto)
    }
}