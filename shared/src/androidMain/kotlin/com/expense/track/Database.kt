package com.expense.track

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.expense.track.data.DataBase.AppDatabase
import com.expense.track.data.DataBase.setUpDataBase

internal fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}

fun initializeDatabase(ctx: Context){
    setUpDataBase(getDatabaseBuilder(ctx))
}