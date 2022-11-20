package com.pvg.pvg2023.base.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * ApiService
 */
interface LoginApiService {

    /** Login Api */
    @POST("login/")
    suspend fun login(
        @Body id: String,
        @Body pass: String
    ): Response<User>
}

/**
 * ログイン レスポンス
 */
data class User(
    /** ID */
    var id: Int?,
    /** 名前 */
    var name: String,
    /** 事業所名 */
    var officeName: String,
    /** 保険者名 */
    var insurerName: String
): BaseApiResponse
