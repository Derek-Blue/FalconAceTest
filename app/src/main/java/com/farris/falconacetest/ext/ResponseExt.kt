package com.farris.falconacetest.ext

import com.farris.falconacetest.service.exception.EmptyBodyException
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response


/**
 * 檢查Http Status是否在200-299，如果是回傳[ResponseBody]，否則拋出[HttpException]。
 */
@Throws(HttpException::class)
fun <T1 : Response<T2>, T2> T1.checkIsSuccessful(): T1 {
    if (this.isSuccessful) {
        return this
    }
    throw HttpException(this)
}

/**
 * 要求[Response]一定要有[ResponseBody]，否則拋出[EmptyBodyException]。
 */
@Throws(EmptyBodyException::class)
fun <T : Response<R>, R> T.requireBody(): R {
    return this.body() ?: throw EmptyBodyException()
}