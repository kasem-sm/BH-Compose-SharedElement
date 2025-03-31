package com.example.bh_assignment.domain

import android.net.Uri
import android.os.Bundle
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import com.example.bh_assignment.R
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.random.Random

val SizeItemNavType = object : NavType<List<SizeItem>>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): List<SizeItem> {
        return bundle.getSerializable(key) as List<SizeItem>
    }

    override fun parseValue(value: String): List<SizeItem> {
        return Json.decodeFromString(value)
    }

    override fun serializeAsValue(value: List<SizeItem>): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: List<SizeItem>) {
        bundle.putSerializable(key, value as java.io.Serializable)
    }

}

@Serializable
@Stable
data class ShoeItem(
    val resId: Int,
    val title: String,
    val price: String,
    @Contextual
    val color: Color,
    @Contextual
    val foregroundColor: Color = Color.White,
    val sizes: List<SizeItem> = SizeItem.listOfSizes,
    val variants: List<Int>
) {
    companion object {
        val listOfShoes = listOf(
            ShoeItem(
                resId = R.drawable.red_shoe,
                title = "Alpha Savage",
                price = "₹8,895",
                color = Color(0xFFE24C4D),
                variants = listOf(
                    R.drawable.red_shoe,
                    R.drawable.yellow_shoe_2,
                    R.drawable.black_shoe,
                )
            ),
            ShoeItem(
                resId = R.drawable.yellow_shoe,
                title = "Air Max 97",
                price = "₹11,897",
                color = Color(0xFFFDBA62),
                foregroundColor = Color.Black,
                variants = listOf(
                    R.drawable.yellow_shoe,
                    R.drawable.yellow_shoe_2,
                    R.drawable.black_shoe,
                )
            ),
            ShoeItem(
                resId = R.drawable.blue_shoe,
                title = "KD13 EP",
                price = "₹12,995",
                color = Color(0xFF4B81F4),
                variants = listOf(
                    R.drawable.blue_shoe,
                    R.drawable.yellow_shoe_2,
                    R.drawable.black_shoe,
                )
            ),
            ShoeItem(
                resId = R.drawable.green_shoe,
                title = "Air Presto by you",
                price = "₹12,995",
                color = Color(0xFF599C99),
                variants = listOf(
                    R.drawable.green_shoe,
                    R.drawable.yellow_shoe_2,
                    R.drawable.black_shoe,
                )
            )
        )
    }
}

@Serializable
@Stable
data class SizeItem(
    val size: Int,
    val isInStock: Boolean
) {
    companion object {
        val listOfSizes = (6..13).map {
            SizeItem(
                size = it,
                isInStock = Random.nextBoolean()
            )
        }
    }
}
