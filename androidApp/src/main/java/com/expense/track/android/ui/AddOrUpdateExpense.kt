package com.expense.track.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.expense.track.android.MyApplication
import com.expense.track.android.R
import com.expense.track.android.viewModel.ExpenseViewModel


@Composable
fun SetUpAddOrUpdateUi(expenseViewModel: ExpenseViewModel){


    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = getString(R.string.enter_amount),
            Modifier
                .absolutePadding(left = 20.dp, top = 100.dp))
        EditTextComposable()
        Text(text = getString(R.string.add_notes),
            Modifier
                .absolutePadding(left = 20.dp, top = 10.dp))
        EditTextComposable(KeyboardType.Text)
    }

}

@Composable
private fun EditTextComposable(keyboardTypeL:KeyboardType=KeyboardType.Number){
    val mutableEnteredText = remember {
        mutableStateOf("")
    }

    val mutableFocusListener = remember {
        mutableStateOf(false)
    }
    Row(modifier = Modifier.absolutePadding(left = 20.dp, top = 6.dp)
        .setBoundry(mutableFocusListener=mutableFocusListener)) {
        TextField(
            modifier = Modifier
                .absolutePadding(left = 6.dp)
                .onFocusEvent {
                    mutableFocusListener.value =  it.isFocused
                },
            value = mutableEnteredText.value,
            onValueChange = {
                mutableEnteredText.value = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardTypeL
            ),
            shape = RectangleShape,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.DarkGray,
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Gray,
                errorIndicatorColor = Color.Red,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}



fun getString(id:Int):String{
    return ContextCompat.getString(MyApplication.instance!!.applicationContext,id)
}

@Composable
fun Modifier.setBoundry(color:Color=Color.Red, mutableFocusListener: State<Boolean>?=null):Modifier{
    val colorBock = mutableFocusListener?.value?.let {
            if (it){
                color
            }else{
                Color.Gray
            }
        }?:color
    return drawBehind {
        val width = size.width
        val height = size.height
        drawLine(
            color = colorBock,
            start = Offset(-3f, 0f),
            end = Offset(width+3, 0f),
            strokeWidth = (2).dp.toPx()
        )
        drawLine(
            color = colorBock,
            start = Offset(0f, 0f),
            end = Offset(0f, height),
            strokeWidth = (2).dp.toPx()
        )
        drawLine(
            color = colorBock,
            start = Offset(-3f, height),
            end = Offset(width+3, height),
            strokeWidth = (2).dp.toPx()
        )

        drawLine(
            color = colorBock,
            start = Offset(width, 0f),
            end = Offset(width, height),
            strokeWidth = (2).dp.toPx()
        )
    }
}