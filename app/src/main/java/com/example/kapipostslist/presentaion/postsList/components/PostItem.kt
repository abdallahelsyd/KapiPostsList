package com.example.kapipostslist.presentaion.postsList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kapipostslist.domain.models.PostItem

/**
 * Created by Abdallah Shehata on 1/19/2024.
 */

@Preview(showBackground = true)
@Composable
fun PostItem(
    postItem: PostItem = PostItem(
        "body", 1, "title", 2
    ),
    onItemClick: (PostItem) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(Color.White)
            .clickable(onClick = { onItemClick(postItem) }),

        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth().padding(5.dp)
        ) {
                Text(

                    text = postItem.title,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 17.sp),
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = postItem.body,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 5,
                    minLines = 3
                )
        }
    }
}
