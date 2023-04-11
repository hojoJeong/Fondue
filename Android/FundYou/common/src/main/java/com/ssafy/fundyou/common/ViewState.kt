package com.ssafy.fundyou.common

sealed class ViewState<T>(
    val value: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ViewState<T>(data)
    class Loading<T> : ViewState<T>()
    class Error<T>(message: String?, data: T? = null) : ViewState<T>(data, message)
}
