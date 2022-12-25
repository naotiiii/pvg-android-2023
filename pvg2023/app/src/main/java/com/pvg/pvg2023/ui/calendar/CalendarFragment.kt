package com.pvg.pvg2023.ui.calendar

import android.content.Intent
import android.os.Bundle
import com.pvg.pvg2023.base.api.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.pvg.pvg2023.R
import com.pvg.pvg2023.databinding.FragmentReviewBinding
import com.pvg.pvg2023.ui.calendar.diaryOfDay.DiaryOfDayActivity
import com.pvg.pvg2023.utility.TimeUtil

/**
 * カレンダー Fragment
 */
class CalendarFragment : Fragment(), CalendarFragmentInterface {

    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!

    /** Presenter */
    private val presenter by lazy {
        CalendarFragmentPresenter(requireContext(), this)
    }

    /** ViewModel */
    private val calendarViewModel by lazy {
        ViewModelProvider(this)[CalendarViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        binding.calendarView.setOnDayClickListener(object : OnDayClickListener{
            override fun onDayClick(eventDay: EventDay) {
                val timeMillis = eventDay.calendar.timeInMillis
                Log.d("Selected Calendar Day = ${TimeUtil.calendarTimeToDate(timeMillis)}")
                transitionToDiaryOfDay(timeMillis)
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.setImageCalendarOfDay(binding.calendarView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * 日毎の記録画面に遷移
     * @param unitTime: 選択されたUnitTime
     */
    private fun transitionToDiaryOfDay(unitTime: Long) {
        val intent = Intent(requireContext(), DiaryOfDayActivity::class.java)
        intent.putExtra(DiaryOfDayActivity.ARGS_DATE_KEY, unitTime)
        startActivity(intent)
    }
}

/**
 * Calendar Fragment Interface
 */
interface CalendarFragmentInterface {

}
