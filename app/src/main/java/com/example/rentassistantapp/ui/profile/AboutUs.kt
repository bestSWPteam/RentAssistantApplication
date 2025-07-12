package com.example.rentassistantapp.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentassistantapp.R
import com.example.rentassistantapp.ui.theme.Grey2
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme
import com.example.rentassistantapp.ui.theme.WhiteBase

@Composable
fun AboutUsScreen(modifier: Modifier = Modifier) {
    var image: Painter = painterResource(R.drawable.aboutus)

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold()
        { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = WhiteBase)
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = image,
                    contentDescription = "Official logo",
                    modifier = modifier.size(300.dp).padding(start = 24.dp)
                )
                Text(
                    text = "Добро пожаловать в нашу компанию! Мы специализируемся на выполнении " +
                            "задач за, предлагая нашим клиентам удобное и эффективное решение для" +
                            " реализации их идей и потребностей. Наша команда состоит из опытных " +
                            "профессионалов, готовых взять на себя любые задачи — от простых" +
                            " поручений до сложных проектов.",
                    fontSize = 24.sp,
                    color = Grey2,
                    modifier = modifier.padding(horizontal = 20.dp, vertical = 24.dp),
                    textAlign = TextAlign.Center,
                    lineHeight = 28.sp
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutUsPreview() {
    RentAssistantAppTheme {
        AboutUsScreen()
    }
}