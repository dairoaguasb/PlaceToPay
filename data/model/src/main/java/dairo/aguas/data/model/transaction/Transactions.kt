package dairo.aguas.data.model.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Dairo Aguas B on 20/04/2020.
 */
@Entity(tableName = "transactions")
data class Transactions(
    @PrimaryKey(autoGenerate = true)
    val idAuto: Int = 0,
    val status: String = "",
    val message: String = "",
    val internalReference: Int = 0,
    val reference: String = "",
    val franchiseName: String = "",
    val price: Int = 0,
    val lastDigits: String = "",
    val imageProduct: String = "",
    val date: String = ""
)