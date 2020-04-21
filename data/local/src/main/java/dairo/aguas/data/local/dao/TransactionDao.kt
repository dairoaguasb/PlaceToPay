package dairo.aguas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import dairo.aguas.data.model.transaction.Transactions

/**
 * Created by Dairo Aguas B on 20/04/2020.
 */
@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transactions: Transactions)
}