package com.pvg.pvg2023.utility

import java.text.SimpleDateFormat
import java.time.*
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
    fun calendarTimeToDate(timeMillis: Long): LocalDateTime {
        val localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timeMillis), ZoneId.systemDefault())
        //val zoneTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault())
        return localDateTime
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