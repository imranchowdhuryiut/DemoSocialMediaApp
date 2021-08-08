package com.imran.demosocialmedia.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.imran.demosocialmedia.utils.Constants

/**
 * Created by Md. Imran Chowdhury on 8/8/2021.
 */

class PrefRepository constructor(val context: Context) {

    companion object {
        private const val SP_FETCH_TIME = "SP_FETCH_TIME"
        private const val SP_GENERES = "SP_GENERES"
    }

    private val mPrefs: SharedPreferences =
        context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)


    fun getSavedCategories  (): Set<String>? {
        return getStringListFromSP(SP_GENERES)
    }

    fun setSavedCategories(value: Set<String>?){
        saveStringSetToSP(value, SP_GENERES)
    }

    private fun saveStringSetToSP(value: Set<String>?, code: String) {
        val e = mPrefs.edit()
        e.putStringSet(code, value)
        e.apply()
    }

    private fun getStringListFromSP(code: String): Set<String>? {
        return mPrefs.getStringSet(code, setOf())
    }


}