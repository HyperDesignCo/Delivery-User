package com.example.delivaryUser.common.data.models

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
/**
 * Crypto utility object for secure data encryption and decryption using Android Keystore.
 *
 * This implementation uses AES encryption with CBC block mode and PKCS7 padding to provide
 * hardware-backed cryptographic operations that are isolated from the Android application process.
 *
 * ## Key Components:
 *
 * ### Algorithm: AES (Advanced Encryption Standard)
 * - A symmetric encryption algorithm that uses the same key for both encryption and decryption
 * - Industry-standard for secure data encryption
 * - Hardware-accelerated on most modern Android devices
 *
 * ### Block Mode: CBC (Cipher Block Chaining)
 * - Chains encrypted blocks together by XORing each plaintext block with the previous ciphertext block
 * - Creates interdependent encrypted blocks, improving security
 * - Requires an Initialization Vector (IV) for the first block
 *
 * ### Padding: PKCS7
 * - Adds padding bytes when data doesn't perfectly fit into fixed-size blocks (16 bytes for AES)
 * - Ensures all blocks are completely filled before encryption
 * - Automatically removed during decryption
 *
 * ### Initialization Vector (IV)
 * - A randomly generated 16-byte value combined with the first block before encryption
 * - Ensures identical plaintext produces different ciphertext each time
 * - Prepended to encrypted data and must be provided for decryption
 *
 * ## Security Features:
 * - Keys stored in Android Keystore (hardware-backed when available)
 * - Keys never exposed to application code
 * - Randomized encryption ensures no deterministic patterns
 * - No user authentication required (can be enabled if needed)
 *
 * ## Usage:
 * ```kotlin
 * // Encrypt data
 * val encrypted = Crypto.encrypt("sensitive data".toByteArray())
 *
 * // Decrypt data
 * val decrypted = Crypto.decrypt(encrypted)
 * val original = String(decrypted, Charsets.UTF_8)
 * ```
 *
 * @property KEY_ALIAS Identifier for the encryption key in Android Keystore
 * @property TRANSFORMATION Complete cipher transformation string: "AES/CBC/PKCS7Padding"
 *
 * Created by Rodina Mo'men 2026-02-9
 */

object Crypto {
    private const val KEY_ALIAS = "secret"
    private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
    private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
    private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
    private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"

    private val cipher = Cipher.getInstance(TRANSFORMATION)
    private val keyStore = KeyStore
        .getInstance("AndroidKeyStore")
        .apply {
            load(null)
        }

    private fun getKey(): SecretKey {
        val existingKey = keyStore
            .getEntry(KEY_ALIAS, null) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: createKey()
    }

    private fun createKey(): SecretKey {
        return KeyGenerator
            .getInstance(ALGORITHM)
            .apply {
                init(
                    KeyGenParameterSpec.Builder(
                        KEY_ALIAS,
                        KeyProperties.PURPOSE_ENCRYPT or
                                KeyProperties.PURPOSE_DECRYPT
                    )
                        .setBlockModes(BLOCK_MODE)
                        .setEncryptionPaddings(PADDING)
                        .setRandomizedEncryptionRequired(true)
                        .setUserAuthenticationRequired(false)
                        .build()
                )
            }
            .generateKey()
    }

    fun encrypt(bytes: ByteArray): ByteArray {
        cipher.init(Cipher.ENCRYPT_MODE, getKey())
        val iv = cipher.iv
        val encrypted = cipher.doFinal(bytes)
        return iv + encrypted
    }

    fun decrypt(bytes: ByteArray): ByteArray {
        val iv = bytes.copyOfRange(0, cipher.blockSize)
        val data = bytes.copyOfRange(cipher.blockSize, bytes.size)
        cipher.init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
        return cipher.doFinal(data)
    }
}