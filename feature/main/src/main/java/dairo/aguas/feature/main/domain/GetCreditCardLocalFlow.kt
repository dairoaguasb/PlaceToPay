package dairo.aguas.feature.main.domain

import dairo.aguas.data.repository.creditcard.CreditCardRepository

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
class GetCreditCardLocalFlow(private val creditCardRepository: CreditCardRepository) {

    fun execute() =
        creditCardRepository.getCreditCardFlow()
}