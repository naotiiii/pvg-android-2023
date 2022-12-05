package com.pvg.pvg2023.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pvg.pvg2023.R
import com.pvg.pvg2023.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /** 今週の目標 EditText */
    private lateinit var weekTargetEditTextView: EditText

    /** GA継続率 TextView */
    private lateinit var gaResultRateTextView: TextView
    /** GA継続率 順位 TextView */
    private lateinit var gaRankingResultTextView: TextView

    /** ダイアリー継続率 TextView */
    private lateinit var diaryResultRateTextView: TextView
    /** ダイアリー継続率 順位 TextView */
    private lateinit var diaryRankingResultTextView: TextView

    /** ゴールド獲得数 TextView */
    private lateinit var obtainGoldResultTextView: TextView
    /** ゴールド獲得数 順位 TextView */
    private lateinit var obtainGoldRankingResultTextView: TextView

    /** 表示件数 プルダウン */
    private lateinit var spinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initView()
        return root
    }

    /**
     * 画面レイアウトの作成
     */
    private fun initView() {
        weekTargetEditTextView = binding.editTextWeekTarget

        gaResultRateTextView = binding.textContinuationRateResults
        gaRankingResultTextView = binding.textSelfRankingGa

        diaryResultRateTextView = binding.textDiaryRateResults
        diaryRankingResultTextView = binding.textSelfRankingDiary

        obtainGoldResultTextView = binding.textObtainGoldResults
        obtainGoldRankingResultTextView = binding.textSelfRankingObtainGold
        settingSpinner()
    }

    /**
     * 表示件数スピナーの設定
     */
    private fun settingSpinner() {
        val displayArray = arrayOf(
            getString(R.string.main_number_of_display_title),
            getString(R.string.main_last_3_month_title),
            getString(R.string.main_last_6_month_title),
            getString(R.string.main_last_12_month_title),
            getString(R.string.main_last_all_month_title),
        )

        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            displayArray
        )

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner = binding.spinnerDisplay
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                println("Spinner Item Selected: item= $item, position= $position")
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}