package com.kanfang123.vrhouse.kanfang.utils

import com.kanfang123.vrhouse.kanfang.base.BaseResponse
import com.kanfang123.vrhouse.kanfang.network.APIException
import com.kanfang123.vrhouse.kanfang.network.ExceptionManager
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * description ：
 * created by simon : 2019/4/18
 */
class RxUtil {
    companion object {
        fun <T> applySchedulers(): Observable.Transformer<T, T> {
            return Observable.Transformer {
                observable ->
                    observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            }
        }

        fun <T> handleResult(): Observable.Transformer<BaseResponse<T>,T> {
            return Observable.Transformer {
                httpResponseObservable -> httpResponseObservable.flatMap {
                    response ->
                    if ("200".equals(response.state)) createData(response.payload)
                    else Observable.error(APIException(response.state,""))
                }
            }

        }

        fun <T> createData(t: T): Observable<T> {
            return Observable.create { subscriber ->
                try {
                    subscriber.onNext(t)
                    subscriber.onCompleted()
                } catch (e: Exception) {
                    subscriber.onError(ExceptionManager.handleException(e))
                }
            }
        }

    }
}