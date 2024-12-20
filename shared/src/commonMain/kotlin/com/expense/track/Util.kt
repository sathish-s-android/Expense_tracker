package com.expense.track

import com.expense.track.data.DataBase.MyDataBase
import com.expense.track.data.DataSource
import com.expense.track.data.Repository
import com.expense.track.data.local.LocalDataSource
import com.expense.track.data.local.LocalRepository
import com.expense.track.usecase.GetExpense
import com.expense.track.usecase.InsertExpense
import com.expense.track.usecase.UpdateExpense


fun getExpenseUseCase():GetExpense{
    return GetExpense(getDataSource())
}

fun getInsertExpense(): InsertExpense {
    return InsertExpense(getDataSource())
}

fun getUpdateExpense(): UpdateExpense {
    return UpdateExpense(getDataSource())
}

private fun getDataSource():DataSource{
    return Repository.getInstance(getLocalDataSource())
}

private fun getLocalDataSource():LocalDataSource{
    return LocalRepository.getInstance(MyDataBase.getDataBase())
}
