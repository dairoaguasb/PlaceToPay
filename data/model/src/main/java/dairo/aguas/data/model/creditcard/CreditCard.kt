package dairo.aguas.data.model.creditcard

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
@Entity(tableName = "credit_card")
data class CreditCard(
    @PrimaryKey()
    val idCreditCard: Int = 1,
    val nameCreditCard: String = "",
    val numberCreditCard: String = "",
    val expirationCard: String = "",
    val ccv: String
)