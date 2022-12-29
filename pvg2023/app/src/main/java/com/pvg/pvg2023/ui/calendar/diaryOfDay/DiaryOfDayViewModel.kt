package com.pvg.pvg2023.ui.calendar.diaryOfDay

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pvg.pvg2023.R
import com.pvg.pvg2023.utility.TimeUtil
import java.time.LocalDateTime
import java.util.*

class DiaryOfDayViewModel(
    application: Application = Application()
): AndroidViewModel(application) {

    /** 日付 */
    val currentDate: LiveData<String>
        get() = _currentDate
    private var _currentDate: MutableLiveData<String> = MutableLiveData("")

    /** Ga値の表示用テキスト */
    val gaValueText: LiveData<String>
        get() = _gaValueText

    private var _gaValueText: MutableLiveData<String> = MutableLiveData("")

    /**
     * LocalDateTIme -> Stringの日付
     * @param localDateTime LocalDateTime
     * @return 日付
     */
    fun setDiaryOfDay(context: Context, date: Date): String {
        val dateText = TimeUtil.convertDateToString(date,
            context.getString(R.string.date_format_yyyy_mm_dd_with_day_of_week))
        _currentDate.value = dateText
        return dateText
    }

    /**
     * 日付が変更された時の処理
     * @param date 変更後Date
     */
    fun changeDate(date: Date) {
        
    }
}