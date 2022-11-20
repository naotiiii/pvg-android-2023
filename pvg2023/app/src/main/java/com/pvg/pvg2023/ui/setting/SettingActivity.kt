package com.pvg.pvg2023.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pvg.pvg2023.databinding.ActivitySettingBinding

/** 設定画面 */
class SettingActivity: AppCompatActivity() {

    lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }



}