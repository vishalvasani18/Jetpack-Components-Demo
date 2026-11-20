package com.example.myapplication.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(){
    Box(modifier = Modifier.fillMaxSize()){

        TextField(value = "",
                    onValueChange = {},
                    modifier = Modifier.align(Alignment.Center))
        Spacer(modifier = Modifier.height(20.dp))
        TextField(value = "",
            onValueChange = {},
            modifier = Modifier.align(Alignment.Center))
    }

}