package com.example.delivaryUser.feature.fastorder.ui.view

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.delivaryUser.R
import com.example.delivaryUser.common.data.repository.remote.File
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.components.textfield.DelivaryUserTextInputField
import com.example.delivaryUser.common.ui.components.textfield.DeliveryUserTextInputFieldDefaults
import com.example.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.fastorder.ui.viewmodel.FastOrderContract
import com.example.delivaryUser.feature.fastorder.ui.viewmodel.FastOrderViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FastOrderScreen(viewModel: FastOrderViewModel = koinViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.onActionTrigger(FastOrderContract.Action.Init)
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    FastOrderContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
private fun FastOrderContent(state: FastOrderContract.State, action: (FastOrderContract.Action) -> Unit) {
    DelivaryUserScreen(
        header = {
            DelivaryUserTopBar(onStartIconClicked = {}, startIcon = R.drawable.ic_notification)
        }) {
        val context = LocalContext.current
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->
            uri?.let {
                val file = convertImage(
                    context, it.toString()
                )
                file?.let {
                    action(
                        FastOrderContract.Action.ImageSelectionAction.SelectFile(
                            imageFile = file, imageUri = uri
                        )
                    )
                }
            }
        }
        FastOrderLocation(location = state.location)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 16.dp),
                text = stringResource(R.string.order_details),
                style = DelivaryUserTheme.typography.headline.medium,
                color = DelivaryUserTheme.colors.secondary
            )
            DelivaryUserTextInputField(
                modifier = Modifier
                    .height(88.dp)
                    .fillMaxWidth()
                    .padding(top = 7.dp),
                maxLines = 3,
                value = state.orderDetails,
                colors = DeliveryUserTextInputFieldDefaults.color(
                    focusedBorderColor = DelivaryUserTheme.colors.secondary,
                    unfocusedBorderColor = DelivaryUserTheme.colors.secondary.copy(alpha = 0.1f),
                    disabledBorderColor = DelivaryUserTheme.colors.secondary,
                    contentColor = DelivaryUserTheme.colors.secondary,
                    unfocusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
                    focusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
                ),
                onValueChange = { action(FastOrderContract.Action.OnOrderDetailsChanged(it)) },
                placeholder = stringResource(id = R.string.add_order_specific_details),
                textStyle = DelivaryUserTheme.typography.body.medium,
            )
            DelivaryUserTextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                value = "",
                onValueChange = {},
                enabled = false,
                colors = DeliveryUserTextInputFieldDefaults.color(
                    focusedBorderColor = DelivaryUserTheme.colors.secondary.copy(alpha = 0.1f),
                    unfocusedBorderColor = DelivaryUserTheme.colors.secondary.copy(alpha = 0.1f),
                    disabledBorderColor = DelivaryUserTheme.colors.secondary.copy(alpha = 0.1f),
                    contentColor = DelivaryUserTheme.colors.secondary,
                    unfocusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
                    focusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
                ),
                placeholder = stringResource(id = R.string.attach_image),
                trailingIconRes = R.drawable.ic_attach_image,
                trailingIconColor = DelivaryUserTheme.colors.secondary,
                onTrailingIconClicked = { launcher.launch("image/*") })
            if (state.images.isNotEmpty()) AttachedImages(images = state.images, action = action)
            DeliveryFeesSection(modifier = Modifier.padding(top = 20.dp), price = state.deliveryCost)
            DelivaryUserButtonPrimary(
                modifier = Modifier.padding(top = 16.dp),
                label = stringResource(R.string.checkout),
                onClick = { action(FastOrderContract.Action.OnCheckOutClicked) },
            )
        }
    }
}

@Composable
private fun FastOrderLocation(location: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RectangleShape, shadow = Shadow(
                    radius = 6.dp, spread = 1.dp, color = DelivaryUserTheme.colors.shadow
                )
            )
            .background(color = DelivaryUserTheme.colors.background.surfaceHigh)
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
            .padding(bottom = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier
                .weight(3f)
                .dropShadow(
                    shape = CircleShape, shadow = Shadow(
                        radius = 6.dp, spread = 1.dp, color = DelivaryUserTheme.colors.shadow
                    )
                )
                .background(color = DelivaryUserTheme.colors.background.surfaceHigh, shape = CircleShape)
                .padding(vertical = 8.dp)
                .padding(start = 24.dp, end = 50.dp),
            text = location,
            style = DelivaryUserTheme.typography.body.large,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun AttachedImages(images: List<FastOrderContract.ImageState>, action: (FastOrderContract.Action) -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .background(
                color = DelivaryUserTheme.colors.background.surfaceHigh, shape = RoundedCornerShape(8.dp)
            )
            .border(
                color = DelivaryUserTheme.colors.secondary.copy(alpha = 0.1f),
                width = 1.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = 8.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        itemsIndexed(images.take(5)) { index, imageState ->
            AttachedImageItem(
                uri = imageState.image.toUri(),
                onImageRemovedClicked = { action(FastOrderContract.Action.OnImageRemoveClicked(index)) })
        }
    }
}

@Composable
private fun AttachedImageItem(uri: Uri, onImageRemovedClicked: () -> Unit) {
    Box(modifier = Modifier.size(38.dp)) {
        Image(
            painter = rememberAsyncImagePainter(uri),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .size(38.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            modifier = Modifier
                .size(14.dp)
                .clickableWithNoRipple {
                    onImageRemovedClicked()
                }, painter = painterResource(R.drawable.img_remove_attached_image), contentDescription = null
        )
    }
}

@Composable
private fun DeliveryFeesSection(price: Double, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(
                color = DelivaryUserTheme.colors.background.surfaceHigh, shape = RoundedCornerShape(8.dp)
            )
            .border(width = 1.dp, color = DelivaryUserTheme.colors.secondary.copy(alpha = 0.1f))
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.delivery_fess),
            style = DelivaryUserTheme.typography.title.medium,
            color = DelivaryUserTheme.colors.secondary
        )
        Text(
            text = stringResource(id = R.string.egp).plus(" ").plus(price),
            style = DelivaryUserTheme.typography.title.medium,
            color = DelivaryUserTheme.colors.secondary
        )
    }
}

private fun convertImage(context: Context, imageUriString: String): File? {
    val uri = imageUriString.toUri()
    return runCatching {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val bytes = inputStream.readBytes()
        val mimeType = context.contentResolver.getType(uri) ?: "application/octet-stream"
        val mimeTypeEnum = File.MimeTypeInfo.fromMime(mimeType)
        val name = uri.lastPathSegment?.substringAfterLast("/") ?: "file_${System.currentTimeMillis()}"
        val size = bytes.size
        inputStream.close()
        File(
            name = name, value = bytes, mimType = mimeTypeEnum, size = size
        )
    }.getOrNull()
}

@Composable
@Preview
private fun FastOrderScreenPreview() = DelivaryUserTheme {
    FastOrderContent(state = FastOrderContract.State(), action = {})
}