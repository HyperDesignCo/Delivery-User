package com.example.delivaryUser.common.domain.remote

import com.example.delivaryUser.common.data.repository.remote.File

interface IFile {
    val name: String
    val value: ByteArray
    val mimType: File.MimeTypeInfo
    val size: Int
}