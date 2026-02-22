package com.example.delivaryUser.feature.cancelorder.ui.view

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.R
import com.example.delivaryUser.common.data.repository.remote.File
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.components.textfield.DelivaryUserTextInputField
import com.example.delivaryUser.common.ui.components.textfield.DeliveryUserTextInputFieldDefaults
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.cancelorder.ui.components.CancelOrderDialog
import com.example.delivaryUser.feature.cancelorder.ui.viewmodel.CancelOrderContract
import com.example.delivaryUser.feature.cancelorder.ui.viewmodel.CancelOrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CancelOrderScreen(viewModel: CancelOrderViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CancelOrderContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
private fun CancelOrderContent(state: CancelOrderContract.State, action: (CancelOrderContract.Action) -> Unit) {
    DelivaryUserScreen(
        header = {
            DelivaryUserTopBar(
                onStartIconClicked = { action(CancelOrderContract.Action.OnBackClicked) },
                content = {
                    Text(
                        stringResource(R.string.cancel_order),
                        style = DelivaryUserTheme.typography.headline.large,
                        color = DelivaryUserTheme.colors.background.surfaceHigh
                    )
                },
            )
        },
        contentPadding = PaddingValues(vertical = 18.dp, horizontal = 16.dp),
        contentVerticalArrangement = Arrangement.spacedBy(16.dp),
        contentHorizontalAlignment = Alignment.Start
    ) {
        val context = LocalContext.current
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->
            uri?.let {
                val file = convertImage(
                    context, it.toString()
                )
                file?.let { selectedFile ->
                    action(CancelOrderContract.Action.ImageSelectionAction.SelectFile(selectedFile, it))
                }
            }
        }
        Text(
            text = stringResource(R.string.tell_us_why_to_cancel_order),
            style = DelivaryUserTheme.typography.headline.extraSmall,
            color = DelivaryUserTheme.colors.secondary
        )
        DelivaryUserTextInputField(
            modifier = Modifier
                .height(88.dp)
                .fillMaxWidth(),
            maxLines = 3,
            value = state.comment,
            colors = DeliveryUserTextInputFieldDefaults.color(
                focusedBorderColor = DelivaryUserTheme.colors.secondary,
                unfocusedBorderColor = DelivaryUserTheme.colors.secondary.copy(alpha = 0.1f),
                disabledBorderColor = DelivaryUserTheme.colors.secondary,
                contentColor = DelivaryUserTheme.colors.secondary,
                unfocusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
                focusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
            ),
            onValueChange = { action(CancelOrderContract.Action.OnCommentChanged(it)) },
            placeholder = stringResource(id = R.string.comment),
            textStyle = DelivaryUserTheme.typography.body.medium,
        )
        DelivaryUserTextInputField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.imageFile?.name ?: "",
            onValueChange = {},
            enabled = true,
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
        DelivaryUserButtonPrimary(
            modifier = Modifier.padding(top = 8.dp),
            label = stringResource(R.string.cancel_order),
            onClick = { action(CancelOrderContract.Action.OpenDialog) },
        )
        if (state.isDialogVisible) CancelOrderDialog(
            onCancelClicked = { action(CancelOrderContract.Action.OnCancelOrderClicked) },
            onKeepClicked = { action(CancelOrderContract.Action.CloseDialog) })
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
@PreviewAllVariants
private fun CancelOrderScreenPreview() = DelivaryUserTheme {
    CancelOrderContent(state = CancelOrderContract.State(), action = {})
}
