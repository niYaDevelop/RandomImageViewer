package com.example.randomimageviewer.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.randomimageviewer.R
import com.example.randomimageviewer.data.RandomImage
import com.example.randomimageviewer.ViewModelRI
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(viewModelRI: ViewModelRI = viewModel()) {
    val uiState by viewModelRI.uiState.collectAsState()
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        ImagePager(
            pagerState = pagerState,
            contentList = uiState.randomList,
            onLiked = {randomImage -> viewModelRI.likedImage(randomImage)}
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePager(
    pagerState: PagerState,
    contentList: List<RandomImage>,
    onLiked: (RandomImage) -> Unit
    ){
    HorizontalPager(
        state = pagerState,
        pageCount = contentList.size,
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
    ) { page ->
        ImageCard(
            randomImage = contentList[page],
            onLiked = {onLiked(contentList[page])},
            Modifier
                .padding(8.dp)
        )
    }
}

@Composable
fun ImageCard(
    randomImage: RandomImage,
    onLiked: () -> Unit,
    modifier:Modifier = Modifier
){
    Surface(
        shape = MaterialTheme.shapes.large,
        modifier =  modifier.aspectRatio(1f),
        color = MaterialTheme.colorScheme.onSurface,
        shadowElevation = 8.dp
    ){
        Box (modifier = Modifier.fillMaxSize(),){
            var scale by remember{ mutableStateOf(ContentScale.Crop) }
            AsyncImage(
                model = randomImage.linkURL,
                contentDescription = null,
                contentScale = scale,
                placeholder = painterResource(id = R.drawable.rand_img_placeholder),
                error = painterResource(id = R.drawable.rand_img_placeholder_error),
                modifier = Modifier
                    .clickable {
                        scale = when (scale) {
                            ContentScale.Crop -> ContentScale.Fit
                            else -> ContentScale.Crop
                        }
                    }
                    .fillMaxSize()
            )
            FavouriteButton(randomImage.isLiked, onLiked, Modifier.align(Alignment.BottomStart))
        }
    }
}

@Composable
fun FavouriteButton(
    isLiked: Boolean,
    onLiked: ()-> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = MutableInteractionSource()
    val coroutineScope = rememberCoroutineScope()
    val scale = remember { Animatable(1f) }

    Icon(
        imageVector = if(isLiked){Icons.Filled.Favorite}
                    else{Icons.Default.FavoriteBorder},
        contentDescription = null,
        tint = if (isLiked) Color.Red else Color.LightGray,
        modifier = modifier
            .padding(8.dp)
            .scale(scale = scale.value)
            .size(size = 48.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onLiked()
                coroutineScope.launch {
                    scale.animateTo(
                        1.3f,
                        animationSpec = tween(30),
                    )
                    scale.animateTo(
                        1f,
                        animationSpec = tween(30),
                    )
                }
            }
    )
}