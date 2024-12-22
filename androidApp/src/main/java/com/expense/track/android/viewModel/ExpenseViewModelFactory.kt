package com.expense.track.android.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.expense.track.getExpenseUseCase
import com.expense.track.getInsertExpense
import com.expense.track.getUpdateExpense
import com.expense.track.usecase.GetExpense
import com.expense.track.usecase.InsertExpense
import com.expense.track.usecase.UpdateExpense

class ExpenseViewModelFactory:ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            GetExpense::class.java,
            InsertExpense::class.java,
            UpdateExpense::class.java
        ).newInstance(
            getExpenseUseCase(),
            getInsertExpense(),
            getUpdateExpense()
        )
    }
}




