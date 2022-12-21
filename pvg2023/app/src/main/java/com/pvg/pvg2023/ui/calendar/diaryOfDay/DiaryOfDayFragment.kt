package com.pvg.pvg2023.ui.calendar.diaryOfDay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pvg.pvg2023.databinding.FragmentDiaryOfDayBinding

/**
 * 日毎の記録画面(カレンダー)
 */
class DiaryOfDayFragment: Fragment() {

    /** Binding */
    private lateinit var binding: FragmentDiaryOfDayBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDiaryOfDayBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            parentFragmentManager.popBackStack()

        }


        return binding.root
    }
}