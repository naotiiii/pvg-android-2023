package com.pvg.pvg2023.base.api

import android.content.Context
import com.pvg.pvg2023.R
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Callback

/**
 * ApiService
 */
interface LoginApiService: BaseApiService {

    /** Login Api */
    @POST("login/")
    suspend fun post(
        @Body id: String,
        @Body pass: String
    ): Call<ResponseBody>
}

/**
 * ログイン APISerVice
 */
class LoginAPI(
    private val context: Context,
    val id: String,
    val pass: String): BaseWrapperAPI() {

    /** Retrofit */
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(context.getString(R.string.url_login_api))
        .build()

    /**
     * ログイン処理
     */
    suspend fun post(callBack: CallBack<User>) {
        val service = retrofit.create(LoginApiService::class.java)
        val load = service.post(id, pass)
        getAPI(load, object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {

            }

            override fun onFailure(call: Call<Response>, t: Throwable) {

            }
        })
    }

    override fun create(response: Response?): BaseApiResponse {
        return User(55, "名前", "null", "null")
    }
}

/**
 * ログイン レスポンス
 */
data class User(
    /** ID */
    var id: Int?,
    /** 名前 */
    var name: String?,
    /** 事業所名 */
    var officeName: String?,
    /** 保険者名 */
    var insurerName: String?
): BaseApiResponse
