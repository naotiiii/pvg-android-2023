package com.pvg.pvg2023.ui.setting

import com.pvg.pvg2023.model.UserInfo

/**
 * SettingPresenter
 */
interface SettingPresenter {

    /**
     * 名前・ユーザID・事業所名称・保険者名称
     * @param userInfo 保険者名称
     */
    fun setProfileText(userInfo: UserInfo)
}