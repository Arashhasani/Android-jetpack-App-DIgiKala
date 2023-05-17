package com.example.dgkala.ui.screens.Login

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dgkala.R
import com.example.dgkala.ui.theme.cursorColor
import com.example.dgkala.ui.theme.deSelectedBottomBar
import com.example.dgkala.ui.theme.searchBarBg

@Composable
fun MyEditText(text:String,onvalueCange:(it:String)->Unit,placeHolder:String){

    TextField(value = text, onValueChange = {onvalueCange(it)}
    , modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(horizontal = 14.dp), shape = RoundedCornerShape(8.dp),
    colors = TextFieldDefaults.textFieldColors(
        backgroundColor = searchBarBg,
        focusedIndicatorColor = deSelectedBottomBar,
        unfocusedIndicatorColor = searchBarBg,
        cursorColor =cursorColor
    ),
    placeholder = {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = placeHolder, style = MaterialTheme.typography.h6, color = Color.LightGray)

        }
    })

}