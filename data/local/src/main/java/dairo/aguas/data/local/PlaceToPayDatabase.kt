package dairo.aguas.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dairo.aguas.data.local.dao.UserDao
import dairo.aguas.data.model.user.User

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */
@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class PlaceToPayDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PlaceToPayDatabase::class.java,
                "PlaceToPay.db"
            )
                .fallbackToDestructiveMigration()
                .setJournalMode(JournalMode.TRUNCATE)
                .build()
    }
}