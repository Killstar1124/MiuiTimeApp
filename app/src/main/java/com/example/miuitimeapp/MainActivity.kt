package com.example.miuitimeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeAppScreen()
        }
    }
}

@Composable
fun TimeAppScreen() {
    var currentTime by remember { mutableStateOf("") }
    val timer = remember { SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = timer.format(Date())
            delay(10)
        }
    }

    val servers = listOf("系统", "北京时间 (阿里)", "淘宝", "京东", "拼多多", "云闪付", "中信银行", "中国银行")
    var selectedServer by remember { mutableStateOf("北京时间 (阿里)") }

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF191A20)).padding(24.dp)
    ) {
        Text("时间", color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 24.dp, bottom = 40.dp))
        
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = selectedServer, color = Color(0xFF888B9A), fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = currentTime, color = Color(0xFFD0D3E5), fontSize = 52.sp, fontWeight = FontWeight.Light)
        }

        Spacer(modifier = Modifier.height(40.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(servers) { server ->
                val isActive = server == selectedServer
                Card(
                    onClick = { selectedServer = server },
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = if (isActive) Color(0xFF7A82A6) else Color(0xFF323544)),
                    modifier = Modifier.height(80.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = server, color = if (isActive) Color.White else Color(0xFFD0D3E5), fontSize = 16.sp, fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal)
                    }
                }
            }
        }
    }
}