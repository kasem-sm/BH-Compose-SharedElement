package com.example.bh_assignment.features.detail

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bh_assignment.core.ui.ExpandableText
import com.example.bh_assignment.R
import com.example.bh_assignment.domain.ShoeItem
import com.example.bh_assignment.domain.SizeItem
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class
)
@Composable
fun SharedTransitionScope.DetailsScreen(
    shoeItem: ShoeItem,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val imageAngle = remember { Animatable(-30f) }

    LaunchedEffect(Unit) {
        imageAngle.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 700,
            )
        )
    }

    val transX = remember { Animatable(100f) }
    val transY = remember { Animatable(100f) }
    val _scaleX = remember { Animatable(.9f) }

    LaunchedEffect(Unit) {
        launch {
            transY.animateTo(
                targetValue = -350f,
                animationSpec = tween(
                    durationMillis = 500,
                )
            )
        }

        launch {
            transX.animateTo(
                targetValue = 450f,
                animationSpec = tween(
                    durationMillis = 500,
                )
            )
        }

        launch {
            _scaleX.animateTo(
                targetValue = 2f,
                animationSpec = tween(
                    durationMillis = 300,
                )
            )
        }
    }

    var selectedSize by remember {
        mutableStateOf(shoeItem.sizes.first())
    }

    var selectedVariant by remember {
        mutableStateOf(shoeItem.variants.first())
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {

                },
                navigationIcon = {
                    Row {
                        Spacer(modifier = Modifier.width(16.dp))

                        Icon(
                            painter = painterResource(R.drawable.back),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable(onClick = onBackClick),
                        )
                    }
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                },
                colors = TopAppBarDefaults
                    .topAppBarColors(
                        containerColor = Color.Transparent,
                        scrolledContainerColor = Color.Transparent,
                        actionIconContentColor = Color.Black,
                        navigationIconContentColor = Color.Black
                    ),
            )
        },
    ) { _ ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(top = 320.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = shoeItem.title,
                            fontSize = 26.sp,
                            fontFamily = FontFamily(Font(R.font.avenir_black))
                        )

                        Text(
                            text = shoeItem.price,
                            fontSize = 26.sp,
                            fontFamily = FontFamily(Font(R.font.avalon_book)),
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = .5.sp
                        )
                    }

                    ExpandableText(
                        text = LoremIpsum(50).values.first(),
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color(0xFF7B8A9E),
                            fontFamily = FontFamily(Font(R.font.avalon_book)),
                            lineHeight = 25.sp
                        ),
                    )
                }
            }
            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(18.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(shoeItem.variants) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                                .clickable {
                                    selectedVariant = it
                                }
                        ) {
                            Box(
                                modifier = Modifier
                                    .then(
                                        if (selectedVariant == it) {
                                            Modifier.border(
                                                2.dp,
                                                Color.Black,
                                                RoundedCornerShape(24.dp)
                                            )
                                        } else Modifier
                                    )
                                    .padding(6.dp)
                                    .background(
                                        Color(0xFFF4F4F4),
                                        RoundedCornerShape(24.dp)
                                    )
                            ) {
                                Image(
                                    painter = painterResource(it),
                                    contentDescription = null,
                                    modifier = Modifier.size(95.dp)
                                )
                            }
                        }
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Select Size",
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(R.font.avenir_black))
                    )

                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        maxLines = 2,
                        maxItemsInEachRow = 4,
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // Implement Disable
                        shoeItem.sizes.forEach {
                            val isSelected = selectedSize.size == it.size
                            InputChip(
                                modifier = Modifier
                                    .width(80.dp),
                                selected = isSelected,
                                colors = InputChipDefaults.inputChipColors(
                                    containerColor = Color.White,
                                    selectedContainerColor = Color.White,
                                    selectedLabelColor = Color.Black,
                                    labelColor = Color(0xFF1F2732),
                                ),
                                onClick = {
                                    selectedSize = it
                                },
                                label = {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        contentAlignment = Alignment.Center // Center the text
                                    ) {
                                        Text(
                                            text = "UK ${it.size}",
                                            fontSize = 16.sp,
                                            fontFamily = FontFamily(
                                                Font(
                                                    when (isSelected) {
                                                        true -> R.font.avenir_black
                                                        false -> R.font.avenir_roman
                                                    }
                                                )
                                            ),
                                            modifier = Modifier.padding(
                                                vertical = 12.dp
                                            ),
                                            maxLines = 1,
                                        )
                                    }
                                },
                                border = BorderStroke(
                                    width = if (isSelected) 2.5.dp else 2.0.dp,
                                    color = if (isSelected) Color.Black else Color(0xFFD8D8D8)
                                ),
                                shape = RoundedCornerShape(16.dp),
                            )
                        }
                    }
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = {

                        },
                        colors = ButtonDefaults
                            .buttonColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text(
                            text = "Add to Bag",
                            fontFamily = FontFamily(Font(R.font.avenir_medium)),
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }

        Box {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
                    .sharedElement(
                        state = rememberSharedContentState(key = "shoe_color_${shoeItem.resId}"),
                        animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 300)
                        }
                    )
                    .graphicsLayer {
                        translationX = transX.value
                        translationY = transY.value
                        scaleX = _scaleX.value
                    }
                    .background(
                        color = shoeItem.color,
                        shape = CircleShape
                    )
            )
            Box(
                Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    Modifier
                        .padding(top = 60.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Image(
                        painter = painterResource(id = shoeItem.resId),
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = "shoe_${shoeItem.resId}"),
                                animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 300)
                                }
                            )
                            .graphicsLayer {
                                rotationZ = imageAngle.value
                            },
                        contentDescription = null,
                    )
                }
            }
        }
    }
}
