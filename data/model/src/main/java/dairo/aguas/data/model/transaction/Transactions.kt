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
    var status: String = "",
    var message: String = "",
    var internalReference: Int = 0,
    var reference: String = "",
    var franchiseName: String = "",
    var price: Int = 0,
    var lastDigits: String = "",
    var imageProduct: String = "",
    var date: String = ""
)