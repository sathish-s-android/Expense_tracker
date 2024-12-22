package com.expense.track.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.expense.track.android.R
import com.expense.track.android.viewModel.ExpenseViewModel
import com.expense.track.android.viewModel.ExpenseViewModelFactory
import com.expense.track.bussinessObjects.Expense


class HomeFragment: Fragment() {

    private val viewModel by viewModels<ExpenseViewModel>{
        ExpenseViewModelFactory()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val composeView = ComposeView(requireContext())

        composeView.setContent {
            SetUpScreen(viewModel.expense.collectAsState())
        }

        return composeView
    }


    @Composable
    fun SetUpScreen(expenseListState: State<List<Expense>>){
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items = expenseListState.value, key = { it.id },) {
                    Column {
                        Text(text = it.spending.toString())
                    }
                }
            }

            FloatingBar(modifier = Modifier.align(Alignment.BottomEnd)){

            }
        }
    }

    @Composable
    fun FloatingBar(modifier:Modifier,clickAction:()->Unit){
        FloatingActionButton(modifier = modifier
            .height(90.dp)
            .width(90.dp)
            .padding(16.dp)
            ,
            shape = RoundedCornerShape(10.dp),
            containerColor = Color.Blue,
            contentColor = Color.Black,
            onClick = ({
                clickAction()
            })) {
            Icon(painter = painterResource(R.drawable.baseline_add_24),"add icon",
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp))
        }
    }
}