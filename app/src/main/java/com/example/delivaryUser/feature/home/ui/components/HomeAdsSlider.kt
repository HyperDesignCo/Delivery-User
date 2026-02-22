package com.example.delivaryUser.feature.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.home.ui.viewmodel.HomeContract

@Composable
fun HomeAdsSlider(ads: List<HomeContract.AdUiState>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        val pagerState = rememberPagerState(pageCount = {
            ads.size
        })
        HorizontalPager(
            modifier = Modifier
                .height(123.dp)
                .background(
                    color = DelivaryUserTheme.colors.background.surfaceHigh,
                    shape = RoundedCornerShape(20.dp)
                ),
            state = pagerState
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(123.dp)
                    .fillMaxWidth(),
                model = ads[pagerState.currentPage].image,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        SelectedImageIndicator(
            modifier = Modifier.padding(top = 8.dp),
            imagesNumber = ads.size, selectedIndex = pagerState.currentPage
        )
    }
}

@Composable
private fun SelectedImageIndicator(
    imagesNumber: Int, selectedIndex: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(imagesNumber) { it ->
            val backGroundColor =
                if (it == selectedIndex) DelivaryUserTheme.colors.secondary else DelivaryUserTheme.colors.background.stroke
            HorizontalDivider(
                modifier = Modifier
                    .height(2.dp)
                    .width(28.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(color = backGroundColor)
            )
        }
    }
}

@Composable
@PreviewAllVariants
private fun HomeAdsSliderPreview() = DelivaryUserTheme {
    HomeAdsSlider(
        ads = listOf(
            HomeContract.AdUiState(
                id = ""
            ),
            HomeContract.AdUiState(
                id = ""
            ),
            HomeContract.AdUiState(
                id = ""
            ),
            HomeContract.AdUiState(
                id = ""
            ),
            HomeContract.AdUiState(
                id = ""
            )
        )
    )
}