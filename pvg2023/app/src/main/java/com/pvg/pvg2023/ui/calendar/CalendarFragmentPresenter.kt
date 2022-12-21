package com.pvg.pvg2023.ui.calendar

import android.R
import android.content.Context
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import java.util.*
import kotlin.collections.ArrayList

/**
 * カレンダー View Presenter
 */
class CalendarFragmentPresenter(
    /** コンテキスト */
    private val context: Context,
    /** View */
    private val view: CalendarFragmentInterface?
): CalendarFragmentPresenterInterface {


    override fun setImageCalendarOfDay(calendarView: CalendarView) {
        val calender = Calendar.getInstance()
        // TODO:: 月指定は、0から始まるため -1する
        calender.set(2022, 11, 10)

        val events: MutableList<EventDay> = ArrayList()
        events.add(EventDay(calender, R.drawable.ic_delete))

        calendarView.setEvents(events)
    }
}

/**
 * カレンダー View Presenter Interface
 */
interface CalendarFragmentPresenterInterface {

    /**
     * カレンダーの日付に、Imageをセットする
     */
    fun setImageCalendarOfDay(calendarView: CalendarView)
}