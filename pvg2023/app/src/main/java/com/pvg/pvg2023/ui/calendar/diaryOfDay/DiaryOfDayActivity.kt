package com.pvg.pvg2023.ui.calendar.diaryOfDay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pvg.pvg2023.databinding.ActivityDiaryOfDayBinding
import com.pvg.pvg2023.utility.TimeUtil
import java.time.LocalDateTime
import java.util.*

/**
 * 日毎の記録画面(カレンダー)
 */
class DiaryOfDayActivity: AppCompatActivity() {

    companion object {
        /** Args Key */
        const val ARGS_DATE_KEY = "ARGS_DATE_KEY"
    }

    /** Binding */
    private lateinit var binding: ActivityDiaryOfDayBinding
    /** 日付 */
    private lateinit var basicLocalDate: Date

    /** ViewModel */
    private val viewModel by lazy {
        ViewModelProvider(this)[DiaryOfDayViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDiaryOfDayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val unitTime = intent.getLongExtra(ARGS_DATE_KEY, System.currentTimeMillis())
        basicLocalDate = TimeUtil.calendarTimeToDate(unitTime)
        binding.textDiaryOfDay.text = viewModel.setDiaryOfDay(this, basicLocalDate)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}