package com.example.meditation.model.shared_preferences

import android.content.Context

class PrefRepository(context: Context) {

    private val pref = context.getSharedPreferences(PrefConstants.PREF_APP_NAME, Context.MODE_PRIVATE)
    private val editor = pref.edit()

    private fun String.putString(str: String) {
        editor.putString(this, str).commit()
    }

    private fun String.getString() = pref.getString(this, "")

    fun putEmail(email: String) {
        PrefConstants.PREF_EMAIL.putString(email)
    }

    fun putPassword(password: String) {
        PrefConstants.PREF_PASSWORD.putString(password)
    }

    fun getEmail() = PrefConstants.PREF_EMAIL.getString()

    fun getPassword() = PrefConstants.PREF_PASSWORD.getString()
}