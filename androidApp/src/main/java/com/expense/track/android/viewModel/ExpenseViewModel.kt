package com.expense.track.android.viewModel

import androidx.lifecycle.ViewModel
import com.expense.track.usecase.GetExpense
import com.expense.track.usecase.InsertExpense
import com.expense.track.usecase.UpdateExpense

class ExpenseViewModel(val getExpense: GetExpense,
    val insertExpense: InsertExpense,
    val updateExpense: UpdateExpense):ViewModel() {

        init {

        }

}