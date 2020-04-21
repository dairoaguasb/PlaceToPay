package dairo.aguas.feature.main.domain

import dairo.aguas.data.model.product.Product
import dairo.aguas.data.model.transaction.TransactionResponse
import dairo.aguas.data.model.transaction.Transactions
import dairo.aguas.data.repository.product.ProductRepository
import dairo.aguas.data.repository.transaction.TransactionRepository

/**
 * Created by Dairo Aguas B on 20/04/2020.
 */
class SetTransactionLocal(
    private val transactionRepository: TransactionRepository,
    private val productRepository: ProductRepository
) {

    suspend fun execute(transactionResponse: TransactionResponse) {
        val product = productRepository.getProductByName(transactionResponse.reference)
        val transactions = Transactions()
        transactions.apply {
            status = transactionResponse.status.status
            message = transactionResponse.status.message
            date = transactionResponse.status.date
            internalReference = transactionResponse.internalReference
            reference = transactionResponse.reference
            franchiseName = transactionResponse.franchiseName
            price = transactionResponse.amount.total
            lastDigits = transactionResponse.lastDigits
            imageProduct = product.imageProduct
        }
        transactionRepository.setTransactionLocal(transactions)
    }
}