package com.expense.track.android.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.expense.track.bussinessObjects.Expense
import com.expense.track.usecase.GetExpense
import com.expense.track.usecase.InsertExpense
import com.expense.track.usecase.UpdateExpense
import com.expense.track.usecase.getDateElse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExpenseViewModel(private val getExpense: GetExpense,
                       private val insertExpense: InsertExpense,
                       private val updateExpenseUseCase: UpdateExpense):ViewModel() {

        private val _expense:MutableStateFlow<List<Expense>> = MutableStateFlow(emptyList())
    val expense:StateFlow<List<Expense>> = _expense
        init {
            viewModelScope.launch {
                getExpenseFromLocal()
            }
        }

    private suspend fun getExpenseFromLocal(){
        getExpense(GetExpense.Request()).getDateElse(emptyList()){
        }.also {expenseList->
            _expense.value = expenseList
        }
    }

    suspend fun updateExpense(expense: Expense){
        updateExpenseUseCase(UpdateExpense.Request(expense))
        getExpenseFromLocal()
    }

    suspend fun insertExpense(expense: Expense){
        insertExpense(InsertExpense.Request(expense))
        getExpenseFromLocal()
    }

}