package com.muhammetgundogar.datastorelearningkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey

import androidx.datastore.preferences.createDataStore

import androidx.lifecycle.lifecycleScope


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var dataStore: DataStore<Preferences>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataStore = createDataStore("settings")




        buttonToGoSecondActivity.setOnClickListener {
            if (editTextName.text.toString().trim().isNotBlank()){
                lifecycleScope.launch {
                    save("meg",editTextName.text.toString())
                }


               val intentToNameAppearanceActivity = Intent(applicationContext,NameAppearanceActivity::class.java)
                startActivity(intentToNameAppearanceActivity)

            }



        }


    }

    private suspend fun save(key: String, value: String) {
        val dataStoreKey = preferencesKey<String>(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }




}