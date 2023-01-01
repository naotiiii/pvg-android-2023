package com.pvg.pvg2023.utility

import android.content.Context
import com.pvg.pvg2023.base.api.User
import com.pvg.pvg2023.model.UserInfo

/**
 *  SharedPreference
 */
class SharedPreference(
    /** コンテキスト */
    private val context: Context
) {

    companion object {
        /** Shared Preference File KEY */
        private const val FILE_NAME = "PVG_FILE"

        /** ユーザー情報 ユーザー名　KEY */
        private const val SETTING_USER_NAME_KEY = "SETTING_USER_NAME_KEY"
        /** ユーザー情報 ユーザーID　KEY */
        private const val SETTING_USER_ID_KEY = "SETTING_USER_ID_KEY"
        /** ユーザー情報 事業所名称 KEY */
        private const val SETTING_OFFICE_KEY = "SETTING_OFFICE_KEY"
        /** ユーザー情報 保険者名称　KEY */
        private const val SETTING_INSURANCE_KEY = "SETTING_INSURANCE_KEY"
    }

    /** SharedPreferenceのインスタンスを取得 */
    private val sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    /**
     * ユーザー情報保存
     * @param userInfo UserInfo
     */
    fun saveUserInfo(userInfo: UserInfo) {
        sharedPref.edit()
            .putString(SETTING_USER_NAME_KEY, userInfo.name)
            .putString(SETTING_USER_ID_KEY, userInfo.id)
            .putString(SETTING_OFFICE_KEY, userInfo.office)
            .putString(SETTING_INSURANCE_KEY, userInfo.insurance)
            .apply()
    }

    /**
     * ユーザー情報の取得
     * @return
     */
    fun getUserInfo(): UserInfo {
        val name = sharedPref.getString(SETTING_USER_NAME_KEY, "")
        val id = sharedPref.getString(SETTING_USER_ID_KEY, "")
        val office = sharedPref.getString(SETTING_OFFICE_KEY, "")
        val insurance = sharedPref.getString(SETTING_INSURANCE_KEY, "")
        return UserInfo(name, id, office, insurance)
    }
}