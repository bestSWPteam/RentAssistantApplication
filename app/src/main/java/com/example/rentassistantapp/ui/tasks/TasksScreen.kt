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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.rentassistantapp.ui.navigationBackend.BottomNavigationBar

@Composable
fun TasksScreen(modifier: Modifier = Modifier,
                isSubscription: Boolean,
                onGoToSubscription: () -> Unit,
                onFilter: () -> Unit,

                navController: NavController) {
    var filterImage: Painter = painterResource(R.drawable.filter)


    Scaffold (
        bottomBar = {BottomNavigationBar(navController)}
    ) {
        innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().background(color = WhiteBase).padding(innerPadding)
        ) {
            Row(
                modifier = modifier.fillMaxWidth().padding(top = 24.dp, start = 28.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Мои задачи",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF616163),
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Image(
                    painter = filterImage,
                    contentDescription = "Filter",
                    modifier.size(28.dp, 28.dp).clickable(
                        onClick = onFilter
                    )
                )
            }

            // condition to print tasks or message about NO SUBSCRIPTION
            if (isSubscription) {
                val sampleTasks = listOf(
                    Task("12.05.25", "Выполнено", "Бронь в ресторан", "Иванова Екатерина"),
                    Task("13.05.25", "В процессе", "Отчет по проекту", "Петрова Анна"),
                    Task("14.05.25", "Не выполнено", "Встреча с клиентом", "Сидоров Алексей"),
                    Task("15.05.25", "Выполнено", "Подготовка презентации", "Кузнецова Мария"),
                    Task("16.05.25", "В процессе", "Обновление документации", "Федоров Дмитрий"),
                    Task("17.05.25", "Не выполнено", "Проверка отчетов", "Смирнова Ольга"),
                    Task("18.05.25", "Выполнено", "Организация мероприятия", "Зайцева Наталья"),
                    Task("19.05.25", "В процессе", "Анализ данных", "Морозов Сергей"),
                    Task(
                        "20.05.25",
                        "Не выполнено",
                        "Разработка нового функционала",
                        "Лебедев Андрей"
                    ),
                    Task("21.05.25", "Выполнено", "Согласование бюджета", "Ковалев Ирина"),
                    Task("22.05.25", "В процессе", "Подбор персонала", "Григорьева Светлана"),
                    Task("23.05.25", "Не выполнено", "Проведение тренинга", "Соловьев Николай"),
                    Task(
                        "24.05.25",
                        "Выполнено",
                        "Создание рекламной кампании",
                        "Тихонов Виктория"
                    ),
                    Task("25.05.25", "В процессе", "Обсуждение стратегии", "Павлов Игорь"),
                    Task("26.05.25", "Не выполнено", "Координация проекта", "Семенова Дарья"),
                    Task("27.05.25", "Выполнено", "Подготовка отчета", "Кириллова Анастасия")
                )
                TaskList(sampleTasks)
            } else {
                NoSubscriptionCard(
                    onGoToSubscription = onGoToSubscription
                )
            }

        }
    }
}

data class Task(
    val date: String,
    val status: String,
    val title: String,
    val assistantName: String
)

@Composable
fun UnitTask(
    date: String,
    status: String,
    title: String,
    assistantName: String
) {
    var arrowRight: Painter = painterResource(R.drawable.arrow_right__tasks_)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp, end = 20.dp, top = 8.dp
            )
            .height(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(BorderStroke(1.dp, White), shape = RoundedCornerShape(16.dp))
            .background(color = White),
    ) {
        Row(
            modifier = Modifier.padding(start = 20.dp, top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {  // text and arrow
            Column(
                modifier = Modifier.weight(1f)
            ) {  // text

                Row() { // date and status
                    Text(
                        text = date,
                        fontSize = 14.sp,
                        color = Grey3
                    )
                    Text(
                        text = status,
                        fontSize = 14.sp,
                        color = Grey3,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }
                Text(  // title
                    text = title,
                    fontSize = 16.sp,
                    color = Red2
                )
                Text(
                    text = "Ассистент: " + assistantName,
                    fontSize = 14.sp,
                    color = Grey3
                )
            }
            Image(
                painter = arrowRight,
                contentDescription = null,
                modifier = Modifier.size(40.dp, 40.dp).align(Alignment.CenterVertically)
                    .padding(end = 12.dp)
            )
        }
    }
}

@Composable
fun TaskList(tasks: List<Task>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(tasks) { task ->
            UnitTask(
                date = task.date,
                status = task.status,
                title = task.title,
                assistantName = task.assistantName
            )
        }
    }
}


@Composable
fun NoSubscriptionCard(
    modifier: Modifier = Modifier,
    onGoToSubscription: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(
                start = 24.dp, end = 32.dp, top = 18.dp
            )
            .fillMaxWidth()
            .height(190.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(BorderStroke(1.dp, White), shape = RoundedCornerShape(16.dp))
            .background(color = White)
    ) {
        Column(
            modifier = modifier.padding(start = 24.dp, end = 36.dp)
        ){
            Text(
                text = "К сожалению, список ваших задач пуст, так как вы не приобрели подписку.",
                fontSize = 20.sp,
                color = Grey3,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(
                    top = 24.dp
                )
            )

            Button(  // Red button go to subscription
                onClick = onGoToSubscription,
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth()
                    .padding(
                        top = 24.dp
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red2,
                    contentColor = White
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(text = "Перейти к оформлению", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TasksScreenPreview() {
    val nav = rememberNavController()

    RentAssistantAppTheme {
        TasksScreen(
            onFilter = {},
            onGoToSubscription = {},
            isSubscription = true,
            navController = nav
        )
    }
}