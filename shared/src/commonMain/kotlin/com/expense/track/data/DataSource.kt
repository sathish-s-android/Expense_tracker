package com.expense.track.data

import com.expense.track.bussinessObjects.Expense
import com.expense.track.usecase.Result

interface DataSource {
    suspend fun getAllExpense():Result<List<Expense>>
    suspend fun insertExpense(expense: Expense):Unit
    suspend fun updateExpense(expense: Expense):Unit
}