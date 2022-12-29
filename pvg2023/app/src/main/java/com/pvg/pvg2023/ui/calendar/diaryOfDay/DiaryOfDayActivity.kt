package com.pvg.pvg2023.ui.calendar.diaryOfDay

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pvg.pvg2023.databinding.ActivityDiaryOfDayBinding
import com.pvg.pvg2023.utility.TimeUtil
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
    private lateinit var basicDate: Date

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
        basicDate = TimeUtil.calendarTimeToDate(unitTime)
        binding.textDiaryOfDay.text = viewModel.setDiaryOfDay(this, basicDate)
        binding.buttonPreviousMonth.setOnClickListener {
            onClickPreviousArrow()
        }
        binding.buttonNextMonth.setOnClickListener {
            onClickNextArrow()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * 左矢印タップ処理
     */
    private fun onClickPreviousArrow() {
        // TODO:: タップ後の処理
        basicDate = TimeUtil.calcDate(basicDate, -1)
        binding.textDiaryOfDay.text = viewModel.setDiaryOfDay(this, basicDate)

        viewModel.changeDate(basicDate)
    }

    /**
     * 右矢印タップ処理
     */
    private fun onClickNextArrow() {
        // TODO:: タップ後の処理
        basicDate = TimeUtil.calcDate(basicDate, 1)
        binding.textDiaryOfDay.text = viewModel.setDiaryOfDay(this, basicDate)
    }
}