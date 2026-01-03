package com.iobits.domain.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
/**
 * SharedPreferenceHelper
 *
 * Usage:
 *
 * // Writing values to SharedPreferences
 * SharedPreferenceHelper.setValue("key_string", "Hello World")
 * SharedPreferenceHelper.setValue("key_int", 123)
 * SharedPreferenceHelper.setValue("key_boolean", true)
 * SharedPreferenceHelper.setValue("key_float", 3.14f)
 * SharedPreferenceHelper.setValue("key_long", 123456789L)
 * SharedPreferenceHelper.setValue("key_set", setOf("a", "b", "c"))
 *
 * // Reading values from SharedPreferences
 * val myString: String = SharedPreferenceHelper.getValue("key_string", "")
 * val myInt: Int = SharedPreferenceHelper.getValue("key_int", 0)
 * val myBool: Boolean = SharedPreferenceHelper.getValue("key_boolean", false)
 * val myFloat: Float = SharedPreferenceHelper.getValue("key_float", 0.0f)
 * val myLong: Long = SharedPreferenceHelper.getValue("key_long", 0L)
 * val mySet: Set<String> = SharedPreferenceHelper.getValue("key_set", emptySet())
 *
 * NOTE:
 * - Always call SharedPreferenceHelper.init(context) once in Application class before use.
 * - Only primitive types and Set<String> are supported.
 */

object SharedPreferenceHelper {

    private const val PREF_NAME = "app_shared_prefs"
    private lateinit var sharedPreferences: SharedPreferences

    // Must be called once in Application class or first activity
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // Generic setter
    fun <T> setValue(key: String, value: T) {
        sharedPreferences.edit {
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                is Set<*> -> {
                    @Suppress("UNCHECKED_CAST")
                    if (value.all { it is String }) {
                        putStringSet(key, value as Set<String>)
                    } else {
                        throw IllegalArgumentException("Only Set<String> is supported")
                    }
                }

                else -> throw IllegalArgumentException("Unsupported type")
            }
        }
    }

    // Generic getter with default value
    @Suppress("UNCHECKED_CAST")
    fun <T> getValue(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> sharedPreferences.getString(key, defaultValue) as T
            is Int -> sharedPreferences.getInt(key, defaultValue) as T
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue) as T
            is Long -> sharedPreferences.getLong(key, defaultValue) as T
            is Set<*> -> {
                if (defaultValue.all { it is String }) {
                    sharedPreferences.getStringSet(key, defaultValue as Set<String>) as T
                } else {
                    throw IllegalArgumentException("Only Set<String> is supported")
                }
            }
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    fun remove(key: String) {
        sharedPreferences.edit { remove(key) }
    }

    fun clear() {
        sharedPreferences.edit { clear() }
    }
}
