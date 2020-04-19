package dairo.aguas.data.repository.creditcard

import dairo.aguas.data.local.dao.CreditCardDao
import dairo.aguas.data.model.creditcard.CreditCard

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
class CreditCardRepositoryImpl(
    private val creditCardDao: CreditCardDao
) : CreditCardRepository {

    override suspend fun setCreditCard(creditCard: CreditCard) {
        creditCardDao.insert(creditCard)
    }

    override fun getCreditCardFlow() =
        creditCardDao.getCreditCardFlow()
}