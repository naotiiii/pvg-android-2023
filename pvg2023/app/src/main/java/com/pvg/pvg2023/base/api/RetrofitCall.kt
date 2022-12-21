package com.pvg.pvg2023.base.api

import android.content.Context
import androidx.annotation.WorkerThread
import kotlinx.coroutines.*
import okhttp3.Request

import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Invocation
import retrofit2.Response
import java.util.concurrent.TimeUnit

/**
 * API リクエストクラス
 */
class RetrofitCall<T>(
    /** Context */
    private val context: Context,
    /** Call */
    private val call: Call<T>
): Call<T> {

    override fun isExecuted(): Boolean {
        return call.isExecuted
    }

    override fun cancel() {
        call.cancel()
    }

    override fun isCanceled(): Boolean {
        return call.isCanceled
    }

    override fun request(): Request {
        return call.request()
    }

    override fun execute(): Response<T> {
        return call.execute()
    }

    override fun timeout(): Timeout {
        return Timeout().timeout(10, TimeUnit.MICROSECONDS)
    }

    @WorkerThread
    override fun enqueue(callback: Callback<T>) {
        runBlocking(Dispatchers.IO) {
            // TODO:: 非同期処理
            call.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val retry: Callback<T> = this
                    val invocation = call.request().tag(Invocation::class.java)
                    //val requireAuth: Annotation = invocation.method().getAnnotation(BaseApiService.Require)


                    if (!response.isSuccessful && response.code() == 401) {
                        // 認証切れ
                        // TODO:: 再認証処理
                    } else {
                        java.lang.Runnable {
                            callback.onResponse(call, response)
                        }
                    }

                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    java.lang.Runnable {
                        callback.onFailure(call, t)
                    }
                }
            })
        }
    }

    override fun clone(): Call<T> {
        return RetrofitCall(context, call)
    }
}
