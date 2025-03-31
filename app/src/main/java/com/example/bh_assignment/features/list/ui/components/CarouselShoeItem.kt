package com.example.bh_assignment.features.list.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.EaseOutQuart
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bh_assignment.R
import com.example.bh_assignment.domain.ShoeItem

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CarouselShoeItem(
    shoe: ShoeItem,
    pageOffset: Float,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: () -> Unit
) {
    val angle = lerp(
        start = -70f,
        stop = -30f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val imageAngle: Float by animateFloatAsState(
        targetValue = angle,
        animationSpec = tween(durationMillis = 300, easing = EaseOutQuart)
    )

    Box(
        modifier = Modifier
            .width(280.dp)
            .height(340.dp)
            .sharedElement(
                state = rememberSharedContentState(key = "shoe_color_${shoe.resId}"),
                animatedVisibilityScope,
                boundsTransform = { _, _ ->
                    tween(durationMillis = 300)
                }
            )
            .background(
                color = shoe.color,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 32.dp, start = 20.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = shoe.title,
                fontSize = 26.sp,
                fontFamily = FontFamily(Font(R.font.gotham_bold))
            )
            Text(
                text = shoe.price,
                fontSize = 20.sp,
                color = LocalContentColor.current.copy(alpha = .9f),
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily(Font(R.font.avalon_book))
            )
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight(.8f),
                color = LocalContentColor.current.copy(.5f)
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 80.dp)
                .offset(x = 40.dp)
                .wrapContentSize(unbounded = true),
        ) {
            Image(
                painter = painterResource(id = shoe.resId),
                contentDescription = null,
                modifier = Modifier
                    .size(360.dp)
                    .sharedElement(
                        state = rememberSharedContentState(key = "shoe_${shoe.resId}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 300)
                        }
                    )
                    .graphicsLayer {
                        rotationZ = imageAngle
                    },
                contentScale = ContentScale.Fit
            )
        }
    }
}

fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (1 - fraction) * start + fraction * stop
}