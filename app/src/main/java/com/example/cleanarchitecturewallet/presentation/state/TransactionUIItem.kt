package com.example.cleanarchitecturewallet.presentation.state

data class TransactionUIItem(
    val description: String,
    val value: Float,
    val date: String,
    val color: androidx.compose.ui.graphics.Color
)
