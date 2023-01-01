package com.pvg.pvg2023.ui.setting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pvg.pvg2023.base.api.User
import com.pvg.pvg2023.model.UserInfo
import java.lang.RuntimeException

/**
 * SettingViewModel
 */
class SettingViewModel(
    /** Application */
    application: Application,
    /** SettingPresenter */
    private val presenter: SettingPresenter?
): AndroidViewModel(application) {

    /**
     * ViewModelFactory
     */
    class Factory(
        /** Application */
        private val application: Application,
        /** Setting Presenter */
        private val presenter: SettingPresenter
    ): ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            when (modelClass) {
                SettingViewModel::class.java -> return SettingViewModel(application, presenter) as T
                else -> throw RuntimeException("")
            }
        }
    }


    /**
     * 初期表示処理
     */
    fun setProfileInfo() {
        // TODO:: ユーザー情報を設定
        val userInfo = UserInfo("USER001", "PVG太郎", "株式会社Provigate", "東京支部")
        presenter?.setProfileText(userInfo)
    }
}