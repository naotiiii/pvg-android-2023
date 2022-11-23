package com.pvg.pvg2023.base.api

import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * APIのラッパー
 */
interface CallBack<Response> {
    /**
     * 成功
     * @param response: 成功時のレスポンス
     */
    fun onSuccess(response: Response)
    /** 失敗 */
    fun onFailed(error: Response)
}

/**
 * BaseWrapperAPI
 */
abstract class BaseWrapperAPI {

    companion object {
        private const val LOG_TAG = "BaseWrapperAPI"
    }

    /**
     * 非同期 GETリクエスト
     *
     */
    protected fun getAPI(call: Call<ResponseBody>, callBack: Callback<Response>) {
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: retrofit2.Response<ResponseBody>
            ) {
                if (response.isSuccessful)
                {
                    Log.d(LOG_TAG, "API SUCCESS")
                    response.body().toString().let { json ->

                        Log.d(LOG_TAG, "Response Json: $json")
                    }
                } else
                {
                    val msg = "HTTP error. HTTP status code: ${response.code()}"
                    Log.e(LOG_TAG, msg)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }
        })
    }

    abstract fun create(response: Response?): BaseApiResponse

    /**
     * エラークラス
     */
    class Response(
        /** レスポンス */
        var response: BaseApiResponse?,

        /** エラーコード */
        var errorCode: String?,
        /** メッセージ */
        var message: String?

    )
}



