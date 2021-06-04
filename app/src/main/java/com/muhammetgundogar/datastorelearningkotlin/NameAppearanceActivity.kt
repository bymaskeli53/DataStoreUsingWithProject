package com.muhammetgundogar.datastorelearningkotlin

import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesKey

import androidx.datastore.preferences.createDataStore

import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_name_appearance.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


import java.util.*


class NameAppearanceActivity : AppCompatActivity() {

    private lateinit var dataStore: DataStore<Preferences>
    private var value:String? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_appearance)
        dataStore = createDataStore("settings")
        lifecycleScope.launch {
            value = getData()
          var random = Random()
            var number1 = random.nextInt(value?.length?.minus(1) !! )
            var number2 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                random.ints(number1,value?.length !!).findFirst().asInt
            } else {
                TODO("VERSION.SDK_INT < N")
            }
            val spannableString = SpannableStringBuilder(value)
            spannableString.setSpan(ForegroundColorSpan(Color.RED),
                                   number1,number2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            textViewName.text = spannableString
        //textViewName.text = value ?: "No Value"
        }
      // use spannable string



    }
    private suspend fun getData() : String? {
        val dataStoreKey = preferencesKey<String>("meg")
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]

    }



}