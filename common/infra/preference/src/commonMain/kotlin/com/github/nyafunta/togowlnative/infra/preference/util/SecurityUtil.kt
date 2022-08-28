package com.github.nyafunta.togowlnative.infra.preference.util

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties.BLOCK_MODE_GCM
import android.security.keystore.KeyProperties.ENCRYPTION_PADDING_NONE
import android.security.keystore.KeyProperties.KEY_ALGORITHM_AES
import android.security.keystore.KeyProperties.PURPOSE_DECRYPT
import android.security.keystore.KeyProperties.PURPOSE_ENCRYPT
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

/**
 * https://proandroiddev.com/securing-androids-datastore-ad56958ca6ee
 */
object SecurityUtil {

    private val cipher by lazy {
        Cipher.getInstance("AES/GCM/NoPadding")
    }

    private val charset by lazy {
        Charsets.UTF_8
    }

    private val keyStore by lazy {
        KeyStore.getInstance(PROVIDER).apply {
            load(null)
        }
    }

    private val keyGenerator by lazy {
        KeyGenerator.getInstance(KEY_ALGORITHM_AES, PROVIDER)
    }

    fun encrypt(keyAlias: String, text: String): ByteArray {
        cipher.init(Cipher.ENCRYPT_MODE, generateSecretKey(keyAlias))
        return cipher.doFinal(text.toByteArray(charset))
    }

    fun decrypt(keyAlias: String, encryptedData: ByteArray): String {
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(keyAlias), GCMParameterSpec(128, cipher.iv))
        return cipher.doFinal(encryptedData).toString(charset)
    }

    private fun generateSecretKey(keyAlias: String): SecretKey = keyGenerator.apply {
        init(
            KeyGenParameterSpec
                .Builder(keyAlias, PURPOSE_ENCRYPT or PURPOSE_DECRYPT)
                .setBlockModes(BLOCK_MODE_GCM)
                .setEncryptionPaddings(ENCRYPTION_PADDING_NONE)
                .build()
        )
    }.generateKey()

    private fun getSecretKey(keyAlias: String) =
        (keyStore.getEntry(keyAlias, null) as KeyStore.SecretKeyEntry).secretKey


    private const val PROVIDER = "AndroidKeyStore"
}