package com.hyperdesign.delivaryUser.feature.userdata.editaccount.ui.view

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.data.repository.remote.File
import com.hyperdesign.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.hyperdesign.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.hyperdesign.delivaryUser.common.ui.components.textfield.DeliveryUserTextInputFieldDefaults
import com.hyperdesign.delivaryUser.common.ui.components.textfield.DeliveryUserTextInputFieldDefaults.textAlign
import com.hyperdesign.delivaryUser.common.ui.extension.asString
import com.hyperdesign.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.hyperdesign.delivaryUser.common.ui.filedstate.TextFieldState
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.hyperdesign.delivaryUser.feature.userdata.editaccount.ui.viewmodel.EditAccountContract
import com.hyperdesign.delivaryUser.feature.userdata.editaccount.ui.viewmodel.EditAccountViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditAccountScreen(viewModel: EditAccountViewModel = koinViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.onActionTrigger(EditAccountContract.Action.Init)
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    EditAccountContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
private fun EditAccountContent(
    state: EditAccountContract.State,
    action: (EditAccountContract.Action) -> Unit,
) {
    DelivaryUserScreen(
        header = {
            DelivaryUserTopBar({ action(EditAccountContract.Action.OnBackClicked) }, content = {
                Text(
                    text = stringResource(R.string.edit_account),
                    style = DelivaryUserTheme.typography.headline.large,
                    color = DelivaryUserTheme.colors.background.surfaceHigh
                )
            })
        },
        contentPadding = PaddingValues(horizontal = 16.dp),

        ) {
        Spacer(modifier = Modifier.weight(0.068f))
        AccountImage(
            imageField = state.image,
            openFile = { imageFile, uri ->
                action(
                    EditAccountContract.Action.ImageSelectionAction.SelectFile(
                        imageFile = imageFile,
                        imageUri = uri
                    )
                )
            }
        )
        EditAccountTextField(
            modifier = Modifier.padding(top = 24.dp),
            value = state.name.value,
            onValueChange = { action(EditAccountContract.Action.NameChanged(name = it)) },
            supportingText = state.name.error.asString(),
            label = stringResource(R.string.name),
            onIconClicked = { action(EditAccountContract.Action.OnEditNameClicked) },
            isEnabled = state.isEditNameEnabled
        )
        EditAccountTextField(
            value = state.phone.value,
            onValueChange = { action(EditAccountContract.Action.PhoneChanged(phone = it)) },
            label = stringResource(R.string.phone_number),
            onIconClicked = { action(EditAccountContract.Action.OnEditPhoneClicked) },
            keyboardOptions = DeliveryUserTextInputFieldDefaults.keyboardOptions.copy(
                keyboardType = KeyboardType.Number
            ),
            isEnabled = state.isEditPhoneEnabled,
            supportingText = state.phone.error.asString()
        )
        EditAccountTextField(
            value = state.password.value,
            onValueChange = { },
            label = stringResource(R.string.password),
            onIconClicked = { action(EditAccountContract.Action.OnEditPasswordClicked) },
            isEnabled = false,
        )
        Spacer(modifier = Modifier.weight(0.068f))
        DelivaryUserButtonPrimary(
            label = stringResource(R.string.save_changes),
            onClick = { action(EditAccountContract.Action.OnSaveClicked) },
            isEnabled = state.isSaveEnabled
        )
        Spacer(modifier = Modifier.weight(0.29f))
    }
}

@Composable
private fun AccountImage(imageField: TextFieldState, openFile: (imageFile: File, imageUri: Uri) -> Unit) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            val file = convertImage(
                context,
                it.toString()
            )
            file?.let { openFile(file, uri) }
        }
    }
    val imageColor =
        if (imageField.error == null) Color.Transparent
        else DelivaryUserTheme.colors.primary
    Box(modifier = Modifier.size(90.dp)) {
        AsyncImage(
            modifier = Modifier
                .size(90.dp)
                .clip(shape = CircleShape)
                .background(
                    shape = CircleShape, color = Color.Transparent
                )
                .border(
                    width = 1.5.dp,
                    color = imageColor,
                    shape = CircleShape
                ),
            model = imageField.value.takeIf { it.isNotBlank() },
            contentDescription = null,
            error = painterResource(R.drawable.img_default_user_account),
            placeholder = painterResource(R.drawable.img_default_user_account),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x= 4.dp)
                .padding(top = 12.dp)
                .background(color = DelivaryUserTheme.colors.primary, shape = CircleShape)

        ) {
            Icon(
                modifier = Modifier
                    .padding(4.dp)
                    .size(16.dp)
                    .clickableWithNoRipple { launcher.launch("image/*") },
                imageVector = ImageVector.vectorResource(R.drawable.ic_camera),
                tint = DelivaryUserTheme.colors.background.surfaceHigh,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun EditAccountTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    onIconClicked: () -> Unit,
    modifier: Modifier = Modifier,
    keyboardActions: KeyboardActions = DeliveryUserTextInputFieldDefaults.keyboardActions,
    keyboardOptions: KeyboardOptions = DeliveryUserTextInputFieldDefaults.keyboardOptions,
    isEnabled: Boolean = false,
    supportingText: String? = null,
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        value = value,
        onValueChange = { onValueChange(it) },
        colors = TextFieldDefaults.colors(
            focusedTextColor = DelivaryUserTheme.colors.secondary,
            focusedLabelColor = DelivaryUserTheme.colors.secondaryVariant,
            unfocusedLabelColor = DelivaryUserTheme.colors.secondaryVariant,
            unfocusedTextColor = DelivaryUserTheme.colors.secondary,
            focusedContainerColor = DelivaryUserTheme.colors.secondaryGray,
            disabledTextColor = DelivaryUserTheme.colors.secondary,
            unfocusedContainerColor = DelivaryUserTheme.colors.secondaryGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            disabledContainerColor = DelivaryUserTheme.colors.secondaryGray,
            errorSupportingTextColor = DelivaryUserTheme.colors.primary,
            disabledLabelColor = DelivaryUserTheme.colors.secondaryVariant,
            cursorColor = DelivaryUserTheme.colors.primary
        ),
        label = {
            Text(
                text = label,
                style = DelivaryUserTheme.typography.body.small,
            )
        },
        shape = RoundedCornerShape(8.dp),
        textStyle = DelivaryUserTheme.typography.headline.extraSmall,
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .size(18.dp)
                    .clickableWithNoRipple { onIconClicked() },
                imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
                tint = DelivaryUserTheme.colors.primary,
                contentDescription = null
            )
        },
        enabled = isEnabled,
        supportingText = {
            supportingText?.let {
                Text(
                    text = it,
                    color = DelivaryUserTheme.colors.primary,
                    style = DeliveryUserTextInputFieldDefaults.supportingTextStyle,
                    minLines = 1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = textAlign
                )
            }
        }
    )
}

private fun convertImage(context: Context, imageUriString: String): File? {
    val uri = imageUriString.toUri()
    return runCatching {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val bytes = inputStream.readBytes()
        val mimeType = context.contentResolver.getType(uri) ?: "application/octet-stream"
        val mimeTypeEnum = File.MimeTypeInfo.fromMime(mimeType)
        val name =
            uri.lastPathSegment?.substringAfterLast("/") ?: "file_${System.currentTimeMillis()}"
        val size = bytes.size
        inputStream.close()
        File(
            name = name,
            value = bytes,
            mimType = mimeTypeEnum,
            size = size
        )
    }.getOrNull()
}

@Composable
@PreviewAllVariants
private fun EditAccountScreenPreview() = DelivaryUserTheme {
    EditAccountContent(
        state = EditAccountContract.State(),
        action = {}
    )
}