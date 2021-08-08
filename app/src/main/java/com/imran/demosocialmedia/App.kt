package com.imran.demosocialmedia

import android.app.Application
import com.imran.demosocialmedia.data.repository.PrefRepository

/**
 * Created by Md. Imran Chowdhury on 8/8/2021.
 */
class App: Application() {

    companion object {
        lateinit var appPref: PrefRepository
    }


    override fun onCreate() {
        super.onCreate()

        appPref = PrefRepository(this)
    }
}