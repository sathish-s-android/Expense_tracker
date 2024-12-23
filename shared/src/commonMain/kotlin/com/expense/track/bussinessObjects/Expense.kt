package com.expense.track.bussinessObjects

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val spendTime: Long,
    val spendingDate:String,
    val category: String,
    val spending: Int
)

@Entity
data class Category(@PrimaryKey(autoGenerate = true) val id: Int =0, val name: String)