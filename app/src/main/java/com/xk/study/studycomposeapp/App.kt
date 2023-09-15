package com.xk.study.studycomposeapp

import android.content.Context
import androidx.multidex.MultiDexApplication

class App : MultiDexApplication() {

    init {
        instance = requireNotNull(this)
    }

    companion object {
        private lateinit var instance: App

        fun applicationContext(): Context {
            return instance
        }
    }

}