package com.expense.track.data

import com.expense.track.bussinessObjects.Category
import com.expense.track.bussinessObjects.Expense
import com.expense.track.data.local.LocalDataSource
import com.expense.track.usecase.Result

class Repository(private val localDataSource: LocalDataSource):DataSource {

    companion object{
        private lateinit var instance:Repository
        fun getInstance(localDataSource: LocalDataSource):DataSource{
            return if (!::instance.isInitialized){
                instance = Repository(localDataSource)
                instance
            }else{
                instance
            }
        }
    }

    override suspend fun getAllExpense(): Result<List<Expense>> {
        return localDataSource.getAllExpeneses()
    }

    override suspend fun insertExpense(expense: Expense) {
        return localDataSource.insertExpense(expense)
    }

    override suspend fun updateExpense(expense: Expense) {
        return localDataSource.updateExpense(expense)
    }

    override suspend fun getAllCategory(): Result<List<Category>> {
        return localDataSource.getAllCategory()
    }

    override suspend fun insertCategory(category: Category) {
        localDataSource.insertCategory(category)
    }
}