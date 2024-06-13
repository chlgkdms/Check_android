package com.example.check.data.local.storage

import android.content.Context
import android.preference.PreferenceManager
import com.example.check.data.local.storage.AuthDataStorageImpl.Key.ACCESS_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthDataStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AuthDataStorage {
    override fun setAccessToken(token: String?) {
        getSharedPreference().edit().let {
            it.putString(ACCESS_TOKEN, token)
            it.apply()
        }
    }

    override fun fetchAccessToken(): String = getSharedPreference().getString(ACCESS_TOKEN, "")!!

    override fun clearAccessToken() {
        getSharedPreference().edit().clear().apply()
    }

    private fun getSharedPreference() =
        PreferenceManager.getDefaultSharedPreferences(context)

    private object Key {
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
    }
}
