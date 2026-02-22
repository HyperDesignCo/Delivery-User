package com.hyperdesign.delivaryUser.feature.address.mapview.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun SearchLocationBar(
    query: String, onQueryChange: (String) -> Unit, onClear: () -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = DelivaryUserTheme.colors.background.stroke,
                shape = RoundedCornerShape(10.dp)
            ),
        placeholder = {
            Text(
                stringResource(R.string.search_for_a_location),
                style = DelivaryUserTheme.typography.body.medium,
                color = DelivaryUserTheme.colors.background.hint
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_map_mark),
                contentDescription = "Search",
                tint = DelivaryUserTheme.colors.primary,
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = onClear) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "Clear",
                        tint = DelivaryUserTheme.colors.primary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        },
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor =  DelivaryUserTheme.colors.secondary,
            unfocusedTextColor = DelivaryUserTheme.colors.secondary,
            focusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
            unfocusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh
        )
    )
}


@Composable
fun SearchResultsList(
    predictions: List<String>, onPredictionClick: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = DelivaryUserTheme.colors.background.surfaceHigh)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 280.dp)
        ) {
            items(predictions) { prediction ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onPredictionClick(prediction) }
                        .padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_map_mark),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = DelivaryUserTheme.colors.secondary
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = prediction,
                        style = DelivaryUserTheme.typography.title.small,
                        color = DelivaryUserTheme.colors.secondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
@PreviewAllVariants
private fun SearchMapPreview() = DelivaryUserTheme {
    SearchLocationBar(query = "test", onQueryChange = {

    }, onClear = {

    })
}
