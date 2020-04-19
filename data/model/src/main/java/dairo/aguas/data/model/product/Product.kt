package dairo.aguas.data.model.product

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val idAuto: Int = 0,
    val nameProduct: String = "",
    val priceProduct: Int = 0,
    val imageProduct: String = ""
)