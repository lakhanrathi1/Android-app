package com.vkarmaedu.vkarma

import android.content.Context
import android.preference.PreferenceManager

class PreferencesHelper(context: Context) {
    private val preference = PreferenceManager.getDefaultSharedPreferences(context)

    var example = preference.getString(STRING, "")
    set(value) = preference.edit().putString(STRING, value).apply()

    companion object {
        private const val STRING = "example"
    }
}