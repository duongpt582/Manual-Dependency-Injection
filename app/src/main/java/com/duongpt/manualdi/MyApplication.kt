package com.duongpt.manualdi

import android.app.Application

class MyApplication: Application() {

    // Instance of AppContainer that will be used by all the Activities of the app
    val appContainer = AppContainer()
}