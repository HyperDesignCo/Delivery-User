package com.example.delivaryUser.common.data.repository.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.example.delivaryUser.common.data.DelivaryUserException
import com.example.delivaryUser.common.domain.local.ILocalDataSourceEnum
import com.example.delivaryUser.common.domain.local.ILocalDataSourceProvider
import kotlinx.coroutines.flow.first

 class LocalDataSourceProvider(private val dataStore: DataStore<Preferences>) :
     ILocalDataSourceProvider {
    private fun <Value> preferencesKey(
        key: ILocalDataSourceEnum,
        type: Class<*>
    ): Preferences.Key<Value> {
        return when (type) {
            Boolean::class.java -> booleanPreferencesKey(key.keyValue)
            Float::class.java -> floatPreferencesKey(key.keyValue)
            Int::class.java -> intPreferencesKey(key.keyValue)
            Long::class.java -> longPreferencesKey(key.keyValue)
            String::class.java -> stringPreferencesKey(key.keyValue)
            Set::class.java -> stringSetPreferencesKey(key.keyValue)
            else -> throw DelivaryUserException.Local.IOOperation()
        } as Preferences.Key<Value>

    }

    override suspend fun <Value> save(key: ILocalDataSourceEnum, value: Value, type: Class<*>) {
        val preferencesKey = preferencesKey<Value>(key, type)
        dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun <Value> read(
        key: ILocalDataSourceEnum,
        defaultValue: Value,
        type: Class<*>
    ): Value {
        val preferencesKey = preferencesKey<Value>(key, type)
        val preferences = dataStore.data.first()
        return preferences[preferencesKey] ?: defaultValue
    }

    override suspend fun <Value> delete(key: ILocalDataSourceEnum, type: Class<*>) {
        val preferencesKey = preferencesKey<Value>(key, type)
        dataStore.edit { preferences ->
            preferences.remove(preferencesKey)
        }
    }
}