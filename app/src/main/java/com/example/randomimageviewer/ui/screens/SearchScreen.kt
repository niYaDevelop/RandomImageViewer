package com.example.randomimageviewer.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.randomimageviewer.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen() {
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            state = pagerState,
            pageCount = Int.MAX_VALUE,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentHeight(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
        ) { page ->
            ImageCard(
                painter = painterResource(R.drawable.astronaut),
                isLiked = false,
                Modifier
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun ImageCard(
    painter:Painter,
    isLiked:Boolean,
    modifier:Modifier = Modifier
){
    Surface(
        shape = MaterialTheme.shapes.large,
        modifier =  modifier,
        color = MaterialTheme.colorScheme.onSurface
    ){
        Box(){
            var scale by remember{ mutableStateOf(ContentScale.Crop) }
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = scale,
                modifier = Modifier
                    .aspectRatio(1f)
                    .clickable {
                        scale = when (scale) {
                            ContentScale.Crop -> ContentScale.Fit
                            else -> ContentScale.Crop
                        }}
            )
        }
    }
}