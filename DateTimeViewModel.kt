package com.example.datatimeapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class DateTimeUiState(
    val selectedDate: LocalDate = LocalDate.now(),
    val selectedTime: LocalTime = LocalTime.now().withSecond(0).withNano(0),
    val formattedDate: String = "",
    val formattedTime: String = ""
)

class DateTimeViewModel : ViewModel() {

    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    private val _uiState = MutableStateFlow(
        DateTimeUiState(
            selectedDate = LocalDate.now(),
            selectedTime = LocalTime.now().withSecond(0).withNano(0),
            formattedDate = LocalDate.now().format(dateFormatter),
            formattedTime = LocalTime.now().withSecond(0).withNano(0).format(timeFormatter)
        )
    )
    val uiState: StateFlow<DateTimeUiState> = _uiState.asStateFlow()

    fun updateDate(year: Int, month: Int, day: Int) {
        val newDate = LocalDate.of(year, month, day)
        _uiState.value = _uiState.value.copy(
            selectedDate = newDate,
            formattedDate = newDate.format(dateFormatter)
        )
    }

    fun updateTime(hour: Int, minute: Int) {
        val newTime = LocalTime.of(hour, minute)
        _uiState.value = _uiState.value.copy(
            selectedTime = newTime,
            formattedTime = newTime.format(timeFormatter)
        )
    }
}
