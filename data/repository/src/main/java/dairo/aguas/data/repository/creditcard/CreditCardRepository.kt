package dairo.aguas.data.repository.creditcard

import dairo.aguas.data.model.creditcard.CreditCard
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
interface CreditCardRepository {

    suspend fun insert(creditCard: CreditCard)

    fun getCreditCardFlow(): Flow<CreditCard?>
}