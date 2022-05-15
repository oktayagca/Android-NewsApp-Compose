package com.example.android_newsapp_compose.ui.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.android_newsapp_compose.R
import com.example.android_newsapp_compose.data.entities.Article

@Composable
fun NewsListItem(
    news: Article,
    onItemClick: (newsDetailUrl:String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Surface() {

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clickable { onItemClick(news.url) },
            ) {

                Image(
                    painter = rememberImagePainter(
                        data = news.urlToImage ?: R.drawable.ic_launcher_background,
                        builder = {
                            size(OriginalSize)
                            scale(Scale.FILL)
                            transformations(RoundedCornersTransformation())
                            placeholder(R.drawable.ic_launcher_background)
                        }
                    ),
                    contentDescription = news.description,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                )

                Text(
                    text = news.title ?:"",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = news.description ?:"",
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

            }
        }
    }

}