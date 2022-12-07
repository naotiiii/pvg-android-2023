package com.pvg.pvg2023.ui.review

import android.R
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.pvg.pvg2023.databinding.FragmentReviewBinding
import java.util.*
import kotlin.collections.ArrayList


/**
 * カレンダー Fragment
 */
class ReviewFragment : Fragment() {

    private var _binding: FragmentReviewBinding? = null

    private lateinit var calendarView: CalendarView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(ReviewViewModel::class.java)

        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setImageOfDay()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * カレンダー日付に画像設定
     */
    fun setImageOfDay() {
        calendarView = binding.calendarView
        val calender = Calendar.getInstance()
        // TODO:: 月指定は、0から始まるため -1する
        calender.set(2022, 11, 10)
        
        val events: MutableList<EventDay> = ArrayList()

        events.add(EventDay(calender, R.drawable.ic_delete))

        calendarView.setEvents(events)
    }
}