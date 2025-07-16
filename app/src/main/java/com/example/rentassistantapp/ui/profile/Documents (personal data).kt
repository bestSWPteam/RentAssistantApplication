package com.example.rentassistantapp.ui.profile


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentassistantapp.ui.theme.Grey3
import com.example.rentassistantapp.ui.theme.Red2
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme
import com.example.rentassistantapp.ui.theme.WhiteBase

@Composable
fun DocumentsScreen() {
    Box(modifier = Modifier.fillMaxSize()){
        Scaffold() { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(WhiteBase)
                    .padding(top = 36.dp,
                        bottom = 12.dp,
                        start = 24.dp,
                        end = 24.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                Text(
                    text = "Политика в отношении обработки персональных данных",
                    fontSize = 24.sp,
                    color = Red2
                )
                Text(
                    text = document,
                    fontSize = 20.sp,
                    color = Grey3,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DocumentScreenPreview() {
    RentAssistantAppTheme {
        DocumentsScreen()
    }
}
