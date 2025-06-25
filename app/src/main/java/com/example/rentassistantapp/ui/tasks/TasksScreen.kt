package com.example.rentassistantapp.ui.tasks

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme
import com.example.rentassistantapp.ui.theme.*
import com.example.rentassistantapp.R
import androidx.compose.ui.graphics.Color
import com.example.rentassistantapp.ui.profile.UsersScreen


@Composable
fun TasksScreen(modifier: Modifier = Modifier,
                onFilter: () -> Unit) {
    var filterImage: Painter = painterResource(R.drawable.filter)

    Row(
        modifier = modifier.fillMaxWidth().padding(top = 72.dp, start = 28.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Мои задачи",
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF616163)
        )
        Image(
            painter = filterImage,
            contentDescription = "Filter",
            modifier.size(28.dp , 28.dp).clickable(
                onClick = onFilter
            )
        )
    }
}

@Composable
fun UnitTask() {

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UsersScreenPreview() {
    RentAssistantAppTheme {
        TasksScreen(
            onFilter = {}
        )
    }
}