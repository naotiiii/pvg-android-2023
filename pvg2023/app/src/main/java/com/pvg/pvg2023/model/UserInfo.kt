package com.pvg.pvg2023.model

/**
 * ユーザー情報
 */
data class UserInfo(
    /** ユーザーID */
    val id: String?,
    /** ユーザー名 */
    val name: String?,
    /** 事業所名称 */
    val office: String?,
    /** 保健名称 */
    val insurance: String?
)
