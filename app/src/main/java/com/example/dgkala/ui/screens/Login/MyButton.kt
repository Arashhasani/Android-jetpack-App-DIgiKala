package com.example.dgkala.ui.screens.Login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dgkala.ui.theme.dgKalaLightRed

@Composable
fun MyButton(text:String,onclick:()->Unit){

    Button(onClick = {onclick() }
    , colors = ButtonDefaults.buttonColors(
            backgroundColor = dgKalaLightRed,
    ), modifier = Modifier.fillMaxWidth().height(54.dp).padding( horizontal = 14.dp), shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text, color = Color.White, style = MaterialTheme.typography.h6, fontWeight = FontWeight.SemiBold)

    }

}