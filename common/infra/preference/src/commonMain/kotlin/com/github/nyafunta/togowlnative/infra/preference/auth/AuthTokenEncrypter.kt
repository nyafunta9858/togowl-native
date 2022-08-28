package com.github.nyafunta.togowlnative.infra.preference.auth

import com.github.nyafunta.togowlnative.infra.preference.util.SecurityUtil
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.ext.getFullName

internal object AuthTokenEncrypter {

    private val keyAlias by lazy {
        this::class.getFullName()
    }

    private val json by lazy {
        Json { encodeDefaults = true }
    }

    fun encrypt(token: String) = SecurityUtil.encrypt(
        keyAlias,
        Json.encodeToString(token)
    )

    fun decrypt(encryptedToken: ByteArray) = SecurityUtil.decrypt(
        keyAlias,
        encryptedToken
    ).let {
        json.decodeFromString<String>(it)
    }

}