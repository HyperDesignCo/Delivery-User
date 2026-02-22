package com.hyperdesign.delivaryUser.common.domain.remote

import com.hyperdesign.delivaryUser.common.data.repository.remote.File

interface IFile {
    val name: String
    val value: ByteArray
    val mimType: File.MimeTypeInfo
    val size: Int
}