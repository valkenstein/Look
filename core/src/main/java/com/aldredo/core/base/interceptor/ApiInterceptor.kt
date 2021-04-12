package com.aldredo.core.base.interceptor

import com.aldredo.core.base.interceptor.model.CookieModel
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor(
    private val cookieModel: CookieModel
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        synchronized(this) {
            return chain.proceed(createRequest(chain))
        }
    }

    private fun createRequest(chain: Interceptor.Chain) =
        chain.request().run {
            val urlWithApiKey = url
                .newBuilder()
                .build()

            newBuilder()
                //.addHeader("Cookie","sid=device:60741db0ac20a32eea5ef2bb:794a1b84")
                .header(cookieModel.id, cookieModel.value)
                .url(urlWithApiKey)
                .build()
        }
}
