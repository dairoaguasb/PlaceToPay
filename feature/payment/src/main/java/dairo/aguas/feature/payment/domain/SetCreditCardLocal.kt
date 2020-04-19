package dairo.aguas.feature.payment.domain

import dairo.aguas.data.model.creditcard.CreditCard
import dairo.aguas.data.repository.creditcard.CreditCardRepository

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
class SetCreditCardLocal(private val creditCardRepository: CreditCardRepository) {

    suspend fun execute(creditCard: CreditCard) {
        creditCardRepository.setCreditCard(creditCard)
    }
}