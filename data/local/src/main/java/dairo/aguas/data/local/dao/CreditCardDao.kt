package dairo.aguas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dairo.aguas.data.model.creditcard.CreditCard
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dairo Aguas B on 19/04/2020.
 */
@Dao
interface CreditCardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(creditCard: CreditCard)

    @Query("SELECT * FROM credit_card")
    fun getCreditCardFlow(): Flow<CreditCard?>
}