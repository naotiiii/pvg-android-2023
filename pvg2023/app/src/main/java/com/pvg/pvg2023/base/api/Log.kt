package com.pvg.pvg2023.base.api

import android.util.Log
import java.util.*

/**
 * LOG
 */
object Log {



    /**
     * DEBUG
     */
    fun d(message: String) {
        Log.d(getTag(), message)
    }

    /**
     * VERBOSE
     */
    fun v(message: String) {
        Log.v(getTag(), message)
    }

    /**
     * INFO
     */
    fun i(message: String) {
        Log.i(getTag(), message)
    }

    /**
     * ERROR
     */
    fun e(message: String) {
        Log.e(getTag(), message)
    }

    /**
     * タグの取得
     */
    private fun getTag(): String {
        val date = Date()
        val className = Throwable().stackTrace[1].className
        val methodName = Throwable().stackTrace[1].methodName
        val line = Throwable().stackTrace[1].lineNumber
        return "$date $className: $methodName $line"
    }
}
