package com.codingwithmitch.mviexample.util

data class DataState<T>(
    var message: Event<String>? = null,
    var loading: Boolean = false,
    var data: Event<T>? = null
)
{
    companion object {

        fun <T> error(
            message: String
        ): DataState<T> {
            return DataState(
                message = Event(message),
                loading = false,
                data = null
            )
        }

        fun <T> loading(
            isLoading: Boolean
        ): DataState<T> {
            return DataState(
                message = null,
                loading = isLoading,
                data = null
            )
        }

        fun <T> data(
            message: String? = null,
            data: T? = null
        ): DataState<T> {
            return DataState(
                message = Event.messageEvent(message),
                loading = false,
                data = Event.dataEvent(data)
            )
        }
    }

    override fun toString(): String {
        return "DataState(\nmessage=$message\n, loading=$loading\n, data=$data)"
    }


}