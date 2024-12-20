package com.expense.track.data.local

import com.expense.track.bussinessObjects.Expense
import com.expense.track.usecase.Result

interface LocalDataSource {
    suspend fun getAllExpeneses(): Result<List<Expense>>
    suspend fun insertExpense(expense: Expense):Unit
    suspend fun updateExpense(expense: Expense):Unit
}