package ru.skillbranch.devintensive.repositories

import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.models.Profile

object PreferencesRepository {

    private const val FIRST_NAME = "FIRST_NAME"
    private const val LAST_NAME = "LAST_NAME"
    private const val ABOUT = "ABOUT"
    private const val REPOSITORY = "REPOSITORY"
    private const val RATING = "RATING"
    private const val RESPECT = "RESPECT"
    private const val APP_THEME = "APP_THEME"

    val prefs: SharedPreferences by lazy {
        val ctx = App.applicationContext()
        PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun getProfile(): Profile? = Profile(
        prefs.getString(FIRST_NAME, "")!!,
        prefs.getString(LAST_NAME, "")!!,
        prefs.getString(ABOUT, "")!!,
        prefs.getString(REPOSITORY, "")!!,
        prefs.getInt(RATING, 0),
        prefs.getInt(RESPECT, 0)
    )

    fun saveProfile(profile: Profile) {
       with(profile) {
           putValue(FIRST_NAME to firstName)
           putValue(LAST_NAME to lastName)
           putValue(ABOUT to about)
           putValue(REPOSITORY to repository)
           putValue(RATING to rating)
           putValue(RESPECT to respect)
       }
    }

    fun saveAppTheme(value: Int) {
        putValue(APP_THEME to value)
    }

    fun getAppTheme() = prefs.getInt(APP_THEME, AppCompatDelegate.MODE_NIGHT_NO)

    private fun putValue(pair: Pair<String, Any>) = with(prefs.edit()) {
        val key = pair.first

        when(val value = pair.second) {
            is String -> this.putString(key, value)
            is Boolean -> this.putBoolean(key, value)
            is Int -> this.putInt(key, value)
            is Float -> this.putFloat(key, value)
            is Long -> this.putLong(key, value)
            else -> error("Only shared preferences can be stored in SharedPreferences")
        }
    }
}
