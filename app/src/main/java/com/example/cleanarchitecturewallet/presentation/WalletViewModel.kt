package com.example.cleanarchitecturewallet.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.cleanarchitecturewallet.domain.usecase.CalculateListSumUseCase
import com.example.cleanarchitecturewallet.domain.usecase.ParseTransactionValueInputUseCase
import com.example.cleanarchitecturewallet.presentation.state.TransactionDialogState
import com.example.cleanarchitecturewallet.presentation.state.TransactionType
import com.example.cleanarchitecturewallet.presentation.state.TransactionUIItem
import com.example.cleanarchitecturewallet.ui.theme.green
import com.example.cleanarchitecturewallet.ui.theme.redOrange
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class WalletViewModel: ViewModel() {

    var transactionDialogState by mutableStateOf( TransactionDialogState() )
        private set

    var totalAmountBeforeComma by mutableStateOf(0)
        private set

    var totalAmountAfterComma by mutableStateOf(0)
        private set

    val transactionList = mutableStateListOf<TransactionUIItem>()

    private val parseTransactionValueInputUseCase = ParseTransactionValueInputUseCase()
    private val calculateListSumUseCase = CalculateListSumUseCase()

    fun onDepositClick(){
        transactionDialogState = transactionDialogState.copy(
            isOpen = true,
            type = TransactionType.Deposit
        )
    }

    fun onWithdrawClick(){
        transactionDialogState = transactionDialogState.copy(
            isOpen = true,
            type = TransactionType.Withdraw
        )
    }

    fun onDismissDialog(){
        transactionDialogState = TransactionDialogState()
    }

    fun onTransactionValueChanged(newValue: String){
        val validationResult = parseTransactionValueInputUseCase(newValue)
        transactionDialogState = transactionDialogState.copy(
            currentValueInput = newValue,
            isConfirmButtonEnabled = validationResult
        )
    }

    fun onTransactionConfirm(){
        val factor = if (transactionDialogState.type == TransactionType.Withdraw) -1 else 1
        val transactionFloatValue =
            transactionDialogState.currentValueInput.toFloatOrNull()?.times(factor)?: return

        val timestamp = System.currentTimeMillis()
        val date = Date(timestamp)

        val transactionUiItem = TransactionUIItem(
            description = if (transactionDialogState.type == TransactionType.Withdraw) "Withdraw" else "Deposit",
            value = transactionFloatValue,
            date = date.toString(),
            color = if(transactionDialogState.type == TransactionType.Withdraw) redOrange else green
        )
        transactionList.add(transactionUiItem)
        setTotalAmount()
        onDismissDialog()
    }

    private fun setTotalAmount(){
        val totalAmount = calculateListSumUseCase(
            transactionList.map { it.value }
        )
        val valueMultipliedWith10Pow2 = (totalAmount * 100).roundToInt()
        totalAmountBeforeComma = valueMultipliedWith10Pow2 / 100
        totalAmountAfterComma = valueMultipliedWith10Pow2.absoluteValue % 100
    }

}