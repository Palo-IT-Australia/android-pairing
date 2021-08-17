package com.paloit.coin.app

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@ExperimentalMaterialApi
@Composable
fun ListScreen() {
    LazyColumn {
        item {
            Text(text = "123")
        }
        item {
            Text(text = "234")
        }
    }
}