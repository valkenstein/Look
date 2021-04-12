package com.aldredo.core.base.interceptor

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class MyCookieJar : CookieJar {
    private var cookies: List<Cookie> = listOf()
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        this.cookies = cookies
//        cookies.add(
//            Cookie.Builder()
//                .name("")
//                .value("")
//                .build()
//        )
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookies
    }
}