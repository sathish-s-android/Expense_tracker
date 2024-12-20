package com.expense.track.usecase

import com.expense.track.bussinessObjects.Expense
import com.expense.track.data.DataSource

class GetExpense(private val dataSource: DataSource):BaseUseCase<GetExpense.Request,List<Expense>>() {

    data class Request(val fromDate:Long = 0L,val untilDate:Long = 0L)

    override suspend fun execute(parameter: Request): List<Expense> {
        return dataSource.getAllExpense().getDateElseThrow()
    }
}
