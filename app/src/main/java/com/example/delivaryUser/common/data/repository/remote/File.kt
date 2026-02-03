package com.example.delivaryUser.common.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IFile
import kotlinx.serialization.Serializable

@Serializable
class File(
    override val name: String = "",
    override val value: ByteArray = ByteArray(0),
    override val mimType: MimeTypeInfo = MimeTypeInfo.PDF,
    override val size: Int = 0,
) : IFile {
    enum class MimeTypeInfo(val mime: String) {
        JPEG("image/jpeg"),
        JPG("image/jpg"),
        PNG("image/png"),
        PDF("application/pdf"),
        UNKNOWN("application/octet-stream");

        companion object {
            fun fromMime(mime: String?): MimeTypeInfo = entries.find { it.mime == mime } ?: UNKNOWN
        }
    }
}