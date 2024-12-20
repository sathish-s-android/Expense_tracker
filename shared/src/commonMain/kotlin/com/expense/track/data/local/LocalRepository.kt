package com.expense.track.data.local

import com.expense.track.bussinessObjects.Expense
import com.expense.track.data.DataBase.AppDatabase
import com.expense.track.data.DataBase.ExpenseDao
import com.expense.track.usecase.Result

class LocalRepository(val dataBase:AppDatabase):LocalDataSource {

    companion object{
        private lateinit var instance: LocalDataSource
        private lateinit var dao: ExpenseDao
        fun getInstance(dataBase:AppDatabase): LocalDataSource {
            return if (!::instance.isInitialized){
                instance = LocalRepository(dataBase)
                dao = dataBase.getDao()
                instance
            }else{
                instance
            }
        }
    }


    override suspend fun getAllExpeneses(): Result<List<Expense>> {
        return try {
             Result.Sucess(dao.getAll())
        }catch (e:Exception){
            Result.Failure(e.message?:"")
        }

    }

    override suspend fun insertExpense(expense: Expense) {
        try {
            dao.insert(expense)
        }catch (E:Exception){

        }
    }

    override suspend fun updateExpense(expense: Expense) {
        try {
            dao.update(expense)
        }catch (E:Exception){

        }
    }
}