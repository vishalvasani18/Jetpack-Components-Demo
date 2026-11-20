package com.example.myapplication.util

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import androidx.core.os.LocaleListCompat
import androidx.appcompat.app.AppCompatDelegate

object LanguageManager {

    const val ENGLISH = "en"
    const val HINDI = "hi"

    fun changeLanguage(
        context: Context,
        language: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val localeManager = context.getSystemService(LocaleManager::class.java)
            localeManager.applicationLocales =
                android.os.LocaleList.forLanguageTags(language)
        } else {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(language)
            )
        }
    }

    fun getCurrentLanguage(context: Context): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val localeManager = context.getSystemService(LocaleManager::class.java)
            localeManager.applicationLocales
                .get(0)
                ?.language
                ?: ENGLISH
        } else {
            AppCompatDelegate.getApplicationLocales()
                .get(0)
                ?.language
                ?: ENGLISH
        }
    }
}