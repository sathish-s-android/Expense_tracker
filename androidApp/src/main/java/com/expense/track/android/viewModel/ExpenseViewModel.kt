package com.expense.track.android.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.expense.track.android.insertDefaultCategory
import com.expense.track.bussinessObjects.Category
import com.expense.track.bussinessObjects.Expense
import com.expense.track.usecase.GetAllCategory
import com.expense.track.usecase.GetExpense
import com.expense.track.usecase.InsertCategory
import com.expense.track.usecase.InsertExpense
import com.expense.track.usecase.UpdateExpense
import com.expense.track.usecase.getDateElse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExpenseViewModel(
    private val getExpense: GetExpense,
    private val insertExpense: InsertExpense,
    private val updateExpenseUseCase: UpdateExpense,
    private val getAllCategory: GetAllCategory,
    private val insertCategory: InsertCategory
) : ViewModel() {

    private val _expense: MutableStateFlow<List<Expense>> = MutableStateFlow(emptyList())
    val expense: StateFlow<List<Expense>> = _expense

    private val _category: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    val category: StateFlow<List<Category>> = _category


    private var isCatagoryLoaded = false
    init {
        viewModelScope.launch {
            getExpenseFromLocal()
            getAllCategoryFromLocal()
        }
    }

    private suspend fun getExpenseFromLocal() {
        getExpense(GetExpense.Request()).getDateElse(emptyList()) {
        }.also { expenseList ->
            _expense.value = expenseList
        }
    }

    private suspend fun getAllCategoryFromLocal() {
        getAllCategory(GetAllCategory.Request()).getDateElse(emptyList()) {
        }.also {categoryList->
            _category.value = categoryList
            if (categoryList.isEmpty() && isCatagoryLoaded.not()){
                isCatagoryLoaded = true
                insertDefaultCategory(::insertCategoryTo)
                getAllCategoryFromLocal()
            }
        }
    }

    suspend fun updateExpense(expense: Expense) {
        updateExpenseUseCase(UpdateExpense.Request(expense))
        getExpenseFromLocal()
    }

    suspend fun insertExpense(expense: Expense) {
        insertExpense(InsertExpense.Request(expense))
        getExpenseFromLocal()
    }

    suspend fun insertCategoryTo(category: Category) {
        insertCategory(InsertCategory.Request(category))
        getAllCategoryFromLocal()
    }


}