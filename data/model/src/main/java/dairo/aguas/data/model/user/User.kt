package dairo.aguas.data.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import dairo.aguas.common.utils.Constants
import dairo.aguas.common.utils.getMD5

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */
@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val email: String = "usuario@gmail.com",
    val password: String = "12345".getMD5(),
    val fullName: String = "Dairo Aguas Barraza",
    val phone: String = "+57 3116544396",
    val address: String = "Calle 20 # 74A - 14",
    val urlImage: String = Constants.urlImage
)