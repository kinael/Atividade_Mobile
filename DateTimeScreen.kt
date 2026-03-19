package com.example.datatimeapp.ui.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.datatimeapp.viewmodel.DateTimeViewModel

@Composable
fun DateTimeScreen(viewModel: DateTimeViewModel) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            viewModel.updateDate(year, month + 1, dayOfMonth)
        },
        uiState.selectedDate.year,
        uiState.selectedDate.monthValue - 1,
        uiState.selectedDate.dayOfMonth
    )

    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            viewModel.updateTime(hourOfDay, minute)
        },
        uiState.selectedTime.hour,
        uiState.selectedTime.minute,
        true
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "ViewModel + Componente",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Data formatada: ${uiState.formattedDate}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Hora formatada: ${uiState.formattedTime}")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = uiState.formattedDate,
            onValueChange = {},
            readOnly = true,
            label = { Text("Data") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { datePickerDialog.show() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Selecionar data")
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = uiState.formattedTime,
            onValueChange = {},
            readOnly = true,
            label = { Text("Hora") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { timePickerDialog.show() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Selecionar hora")
        }
    }
}
