package com.expense.track.data.DataBase

import androidx.room.ConstructedBy
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.Update
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.expense.track.bussinessObjects.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

@Database(entities = [Expense::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): ExpenseDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insert(item: Expense)

    @Update
    suspend fun update(item: Expense)

    @Query("SELECT count(*) FROM Expense")
    suspend fun count(): Int

    @Query("SELECT * FROM Expense")
    fun getAllAsFlow(): Flow<List<Expense>>


    @Query("SELECT * FROM Expense")
    suspend fun getAll(): List<Expense>
}



internal fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
        .fallbackToDestructiveMigrationOnDowngrade(false)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}


internal fun setUpDataBase(builder :RoomDatabase.Builder<AppDatabase>){
    MyDataBase.setDataBase(getRoomDatabase(builder))
}

