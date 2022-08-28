package com.github.nyafunta.togowlnative.infra.preference.auth

import com.github.nyafunta.togowlnative.infra.preference.util.SecurityUtil
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.ext.getFullName

internal class EncryptedAuthToken(
    val value: ByteArray
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EncryptedAuthToken

        if (!value.contentEquals(other.value)) return false

        return true
    }

    override fun hashCode(): Int {
        return value.contentHashCode()
    }

    fun decrypt() = SecurityUtil.decrypt(
        keyAlias,
        value
    ).let {
        json.decodeFromString<String>(it)
    }

    companion object {
        private val keyAlias by lazy {
            EncryptedAuthToken::class.getFullName()
        }

        private val json by lazy {
            Json { encodeDefaults = true }
        }

        fun create(token: String) = EncryptedAuthToken(
            SecurityUtil.encrypt(
                keyAlias,
                Json.encodeToString(token)
            )
        )

        fun encode(value: String): ByteArray {
            return Json.encodeToString(value).toByteArray(Charsets.UTF_8)
        }

        fun decode(bytes: ByteArray): String {
            return json.decodeFromString<String>(bytes.toString())
        }

    }
}
