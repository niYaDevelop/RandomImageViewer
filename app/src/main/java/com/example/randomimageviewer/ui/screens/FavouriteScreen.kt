package com.example.randomimageviewer.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.randomimageviewer.R
import com.example.randomimageviewer.RandomImage
import com.example.randomimageviewer.ViewModelRI
import java.util.Random

@Composable
fun FavouriteScreen(viewModelRI: ViewModelRI = viewModel()) {
    val uiState by viewModelRI.uiState.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){items(viewModelRI.favList) { item->FavCard(item, {viewModelRI.openFavImage(item)}) }
    }
    if(uiState.openFavImageDialog) OpenAlert(uiState.favImageToOpen!!,{like -> viewModelRI.likedImage(uiState.favImageToOpen!!, like)}, {viewModelRI.closeFavImage()})
}

@Composable
fun FavCard(randomImage:RandomImage, onClick :()-> Unit){
    Card(
        elevation = 4.dp
    ) {
        AsyncImage(
            model = randomImage.linkURL,
//            painter = randomImage.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.rand_img_placeholder),
            error = painterResource(id = R.drawable.rand_img_placeholder_error),
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(4.dp))
                .border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface)
                )
                .clickable {
                    onClick()
                }
            )
    }
}

@Composable
fun OpenAlert(randomImage: RandomImage, onLiked: (Boolean)-> Unit, onDismiss: ()-> Unit){
    AlertDialog(
        onDismissRequest = onDismiss,
        buttons = {
            ImageCard(
                randomImage,
                onLiked
            )
        }
    )
}