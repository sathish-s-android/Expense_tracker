package com.expense.track.bussinessObjects

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Expense(@PrimaryKey(autoGenerate = true) val id: Int = 0,val spendTime:Long,val category:String,val spending:Int)