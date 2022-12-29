package com.pvg.pvg2023.utility

import java.text.SimpleDateFormat
import java.time.*
import com.pvg.pvg2023.base.api.Log
import java.util.*

/**
 * 時間操作
 */
object TimeUtil {

    /**
     * TimeMillisをDate型に変換 Zone -> Date
     * @param timeMillis
     * @return LocalDateTime
     */
    fun calendarTimeToDate(timeMillis: Long): Date {
        Log.d("Calendar Time To Date = ${Date(timeMillis)}")
        return Date(timeMillis)
    }

    /**
     * 指定したフォーマットで日付を文字列で取得
     * @param date LocalDateTime
     * @param format フォーマット
     * @return 指定した文字列の日付
     */
    fun convertDateToString(date: Date, format: String): String {
        Log.d("Convert Date To String = ${dateToString(date, format)}")
        return dateToString(date, format)
    }

    /**
     * Dateの日付追加
     * @param date 日付
     * @param intervalNum 追加日数
     * @return 追加後にづけ
     */
    fun calcDate(date: Date,intervalNum: Int): Date {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.DATE,intervalNum)
        return cal.time
    }

    /**
     * 指定したフォーマットの日付を文字列で取得
     * @param date: 日付
     * @param format: 日付フォーマット
     * @return 指定した文字列の日付
     */
    fun dateToString(date: Date, format: String): String {
        val df = SimpleDateFormat(format, Locale.getDefault())
        return df.format(date)
    }
}