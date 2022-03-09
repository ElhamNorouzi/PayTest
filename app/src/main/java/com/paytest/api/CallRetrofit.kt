package com.paytest.api

import com.google.gson.Gson
import kotlinx.coroutines.*
import retrofit2.HttpException

object CallRetrofit {

    fun <T> callApi(
        request: Deferred<T>,
        onSuccess: (T) -> Unit,
        onError: (ErrorResponse) -> Unit
    ) {
        GlobalScope.launch {
            try {
                val response = request.await()
                withContext(Dispatchers.Main) {
                    response?.let { onSuccess(it) }
                }
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    if (e is HttpException) {
                        if (e.code() == 400) {
                            if (e.response()!!.raw().priorResponse() != null) {
                                if (e.response()!!.raw().priorResponse()!!.code() == 401) {
                                    /**
                                     * for handle refresh token
                                      */
                                    onError(ErrorResponse("RefreshToken"))
                                }
                            } else {
                                onError(ErrorResponse(e.message()))
                            }
                        } else {
                            onError(getError(e.response()!!.errorBody()!!.string()))
                        }
                    } else {
                        onError(ErrorResponse(e.message.toString()))
                    }

                }
            }
        }
    }

    private fun getError(rawError: String?): ErrorResponse {
        return try {
            Gson().fromJson(rawError, ErrorResponse::class.java)
        } catch (e: Exception) {
            ErrorResponse(e.toString())
        }
    }
}