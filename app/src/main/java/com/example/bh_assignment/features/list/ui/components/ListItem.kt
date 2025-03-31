package com.example.bh_assignment.features.list.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bh_assignment.R

@Composable
fun ListShoeItem(
    modifier: Modifier = Modifier,
    title: String,
    resId: Int,
    price: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            painter = painterResource(resId),
            contentDescription = null,
            modifier = Modifier
                .width(144.dp)
        )
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = title,
                fontFamily = FontFamily(Font(R.font.gotham_medium)),
                fontSize = 18.sp,
                color = Color(0xFF1F2732)
            )
            Text(
                text = price,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.avalon_book)),
                fontWeight = FontWeight.SemiBold,
                letterSpacing = .8.sp,
                color = LocalContentColor.current.copy(.5f),
            )
        }
    }
}