package com.example.bh_assignment.features.list

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bh_assignment.domain.ShoeItem
import com.example.bh_assignment.features.list.ui.components.ListShoeItem
import com.example.bh_assignment.R
import com.example.bh_assignment.features.list.ui.components.CarouselShoeItem
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ListScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: (ShoeItem) -> Unit
) {
    var currentFilter by rememberSaveable {
        mutableStateOf("All")
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
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
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                actions = {
                    Icon(
                        painter = painterResource(R.drawable.search),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                },
                colors = TopAppBarDefaults
                    .topAppBarColors(
                        containerColor = Color.White,
                        scrolledContainerColor = Color.White,
                        actionIconContentColor = Color.Black,
                        navigationIconContentColor = Color.Black
                    ),
            )
        },
        containerColor = Color.White,
    ) { innerPadding ->
        val pagerState = rememberPagerState {
            ShoeItem.listOfShoes.size
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(
                top = 16.dp + innerPadding.calculateTopPadding(),
                bottom = 32.dp + innerPadding.calculateBottomPadding(),
                start = 16.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Shoes",
                    fontFamily = FontFamily(Font(R.font.avalon_demi)),
                    fontSize = 32.sp
                )
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        listOf("All", "Air Max", "Presto", "Huarache", "Mercurial")
                    ) {
                        InputChip(
                            selected = currentFilter == it,
                            colors = InputChipDefaults.inputChipColors(
                                containerColor = Color(0xFFF4F4F4),
                                selectedContainerColor = Color.Black,
                                selectedLabelColor = Color.White,
                                labelColor = Color(0xFF1F2732),
                            ),
                            onClick = {
                                currentFilter = it
                            },
                            label = {
                                Text(
                                    text = it,
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.avenir_roman)),
                                    modifier = Modifier.padding(8.dp)
                                )
                            },
                            border = BorderStroke(
                                width = 1.dp,
                                color = Color(0xFFD8D8D8)
                            ),
                            shape = RoundedCornerShape(18.dp)
                        )
                    }
                }
            }
            item {
                HorizontalPager(
                    modifier = Modifier.fillMaxWidth(),
                    state = pagerState,
                    contentPadding = PaddingValues(
                        end = 100.dp
                    ),
                    pageSpacing = 50.dp
                ) { page ->
                    val pageOffset = pagerState.calculateCurrentOffsetForPage(page)
                        .absoluteValue

                    CompositionLocalProvider(
                        LocalContentColor provides ShoeItem.listOfShoes[page].foregroundColor
                    ) {
                        CarouselShoeItem(
                            shoe = ShoeItem.listOfShoes[page],
                            pageOffset = pageOffset,
                            animatedVisibilityScope = animatedVisibilityScope,
                            onClick = {
                                onClick(
                                    ShoeItem.listOfShoes[page],
                                )
                            }
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
            item {
                Text(
                    text = "243 OPTIONS",
                    fontFamily = FontFamily(Font(R.font.avenir_medium)),
                    fontSize = 15.sp,
                    color = Color(0xFF1F2732)
                )
            }
            itemsIndexed(
                listOf(
                    Triple("Undercover React Presto", "₹ 15,000", R.drawable.blue_shoe),
                    Triple("Air Zoom Pegasus 37", "₹ 9,000", R.drawable.red_shoe),
                )
            ) { index, (title, price, resId) ->
                // Add Divider
                if (index == 0 || index > 0) {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        color = Color(0xFFF4F4F4)
                    )
                }
                ListShoeItem(
                    title = title,
                    price = price,
                    resId = resId
                )
            }
        }
    }
}

private fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}
