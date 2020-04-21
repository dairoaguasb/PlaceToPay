package dairo.aguas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dairo.aguas.data.model.transaction.Transactions
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dairo Aguas B on 20/04/2020.
 */
@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transactions: Transactions)

    @Query("SELECT * FROM transactions WHERE internalReference = :internalReference")
    suspend fun getTransactionsByInternalReference(internalReference: Int): Transactions

    @Query("SELECT * FROM transactions ORDER BY idAuto DESC")
    fun getTransactionListFlow(): Flow<List<Transactions>>
}