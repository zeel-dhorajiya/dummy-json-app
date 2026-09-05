package com.example.cleanarchtemplate

import android.content.SharedPreferences

class InMemorySharedPreferences : SharedPreferences {

  private val store = mutableMapOf<String, Any?>()

  override fun getAll(): MutableMap<String, *> = store.toMutableMap()

  override fun getString(key: String, defValue: String?): String? =
    store[key] as? String ?: defValue

  override fun getStringSet(key: String, defValues: MutableSet<String>?): MutableSet<String>? =
    @Suppress("UNCHECKED_CAST") (store[key] as? MutableSet<String>) ?: defValues

  override fun getInt(key: String, defValue: Int): Int =
    (store[key] as? Int) ?: defValue

  override fun getLong(key: String, defValue: Long): Long =
    (store[key] as? Long) ?: defValue

  override fun getFloat(key: String, defValue: Float): Float =
    (store[key] as? Float) ?: defValue

  override fun getBoolean(key: String, defValue: Boolean): Boolean =
    (store[key] as? Boolean) ?: defValue

  override fun contains(key: String): Boolean = store.containsKey(key)

  override fun edit(): SharedPreferences.Editor = Editor()

  override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {}

  override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {}

  inner class Editor : SharedPreferences.Editor {
    private val pending = mutableMapOf<String, Any?>()
    private val removeKeys = mutableSetOf<String>()

    override fun putString(key: String, value: String?): SharedPreferences.Editor {
      pending[key] = value
      return this
    }

    override fun putStringSet(key: String, values: MutableSet<String>?): SharedPreferences.Editor {
      pending[key] = values
      return this
    }

    override fun putInt(key: String, value: Int): SharedPreferences.Editor {
      pending[key] = value
      return this
    }

    override fun putLong(key: String, value: Long): SharedPreferences.Editor {
      pending[key] = value
      return this
    }

    override fun putFloat(key: String, value: Float): SharedPreferences.Editor {
      pending[key] = value
      return this
    }

    override fun putBoolean(key: String, value: Boolean): SharedPreferences.Editor {
      pending[key] = value
      return this
    }

    override fun remove(key: String): SharedPreferences.Editor {
      removeKeys.add(key)
      return this
    }

    override fun clear(): SharedPreferences.Editor {
      pending.clear()
      removeKeys.clear()
      return this
    }

    override fun commit(): Boolean {
      apply()
      return true
    }

    override fun apply() {
      store.putAll(pending)
      removeKeys.forEach { store.remove(it) }
    }
  }
}
