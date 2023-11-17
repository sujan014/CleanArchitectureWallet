package com.example.cleanarchitecturewallet.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cleanarchitecturewallet.ui.theme.white

@Composable
fun WalletOverview(
    valueBeforeComma:()-> Int,
    valueAfterComma:()-> Int,
    onDepositClick:()-> Unit,
    onWithdrawClick:()-> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        IconButton(onClick = {
            onWithdrawClick()
        }){
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Withdraw",
                tint = white,
                modifier = Modifier.size(32.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text= "$",
                fontSize = 24.sp,
                modifier = Modifier.offset(x = 0.dp, y = (-4).dp)
            )

            Text(
                text= "${valueBeforeComma()}",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text= ".",
                fontSize = 24.sp,
                modifier = Modifier.offset(x = 0.dp, y = (-4).dp)
            )
            Text(
                text= "%02d".format(valueAfterComma()),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        }
        IconButton(onClick = { onDepositClick() }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Deposit amount",
                tint = white,
                modifier = Modifier.size(32.dp)
            )
        }
    }

}