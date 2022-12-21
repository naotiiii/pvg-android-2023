package com.pvg.pvg2023.base.api


import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 * APIレスポンス
 */
interface Response<T> {
    /** 成功 */
    data class Success<T>(
        /** ステータスコード */
        val statusCode: Int,
        /** response */
        val response: T
    )

    data class Failure(
        /** ステータスコード */
        val statusCode: Int,
        /** メッセージ */
        val message: String
    )
}

/**
 * OkHttpUtil
 */
class OkHttpUtil {

    companion object {
        /** コネクト タイムアウト */
        private const val CONNECT_TIME_OUT: Long = 10
        /** READ タイムアウト */
        private const val READ_TIME_OUT: Long = 10
        /** Json Media */
        private val JSON_MEDIA =  "application/json; charset=utf-8".toMediaType()
    }

    constructor(context: Context, url: String, method: HttpMethod) {
        this.context = context
        this.url = url
        this.method = method
    }

    /** コンテキスト */
    private var context: Context
    /** url */
    private var url: String
    /** HttpMethod */
    private var method: HttpMethod

    /** body */
    private var body: RequestBody? = null

    /**
     * ボディ設定
     * @param body bodyクラス
     */
    fun <T> setBody(body: T) {
        val gson = Gson().toJson(body)
        println("================ gson body: $gson")
        this.body = gson.toRequestBody(JSON_MEDIA)
        println("================ gson body: $gson")
    }

    /**
     * ボディ設定
     * @param body Mapからの作成
     */
    fun setBody(body: Map<String, Any?>) {
        val formBody = FormBody.Builder()
        body.forEach {
            val value =  if (it.value == null) "null" else it.value.toString()
            formBody.add(it.key, value)
        }
        this.body = formBody.build()
    }

    /**
     * API リクエスト実行
     */
    fun execute(callBack: CallBack<com.pvg.pvg2023.base.api.Response<BaseApiResponse>>) {
        if (!checkInternet()) return

        // リクエストの作成
        val request = Request.Builder().url(url)
        when (method) {
            HttpMethod.GET -> {
                request.get()
            }
            HttpMethod.POST -> {
                if (body == null) {
                    Log.d("APIRequest Method \"$method\": Require body")
                } else {
                    request.post(body!!)
                }
            }
            HttpMethod.PUT -> {
                if (body == null) {
                    Log.d("APIRequest Method \"$method\": Require body")
                } else {
                    request.put(body!!)
                }
            }
            HttpMethod.DELETE -> {
                request.delete()
            }
        }

        // OkHttpクラスの設定
        // TODO:: loggingを追加したいところ
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
            .build()

        try {
            okHttpClient.newCall(request.build()).enqueue(object : Callback{
                override fun onResponse(call: Call, response: Response) {
                    val responseBody = response.body?.string().orEmpty()
                    if (response.isSuccessful) {
                        Log.d("API SUCCESS: responseBody = ${response.body}")



                    } else {
                        Log.d("API Failure: responseBody = ${response.body}")

                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    Log.e("API Failure: error = $e")

                }
            })
        } catch (e: IOException) {
            Log.e("API Error $e")
        }
    }

    /**
     * インターネット接続確認
     */
    private fun checkInternet(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.allNetworks.forEach { netWork ->
            connectivityManager.getNetworkInfo(netWork).apply {
                if (this != null && this.isConnected) {
                    when (this.type) {
                        ConnectivityManager.TYPE_WIFI -> return this.isConnected
                        ConnectivityManager.TYPE_MOBILE -> return this.isConnected
                    }
                }
            }
        }
        return false
    }

    /**
     * HttpMethod
     */
    enum class HttpMethod {
        GET,
        POST,
        PUT,
        DELETE;
    }
}

/**
 * Application Interceptorの設定
 * Interceptorには、ApplicationInterceptor・networkInterceptorがある
 */
private class HeaderInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val newReq = original.newBuilder()
            .header("Content-Type", "application/json; charset=utf-8")
            .method(original.method, original.body)
            .build()
        return chain.proceed(newReq)
    }
}
