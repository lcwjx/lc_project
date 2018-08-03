package com.lee.login

import com.framework.base.BasePresenter
import com.framework.http.*
import com.framework.http.model.BaseResponse
import com.trello.rxlifecycle2.LifecycleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import retrofit2.http.FieldMap

/**
 * Created by lichen on 2018/6/5.
 */
class LoginPresenter<V : LoginContract.View> : BasePresenter<V>(), LoginContract.Presenter<V> {

    /**
     * login请求唯一标识
     */
    private val LOGIN = "login"

    override fun sendSms(filedMap: RequestBody, lifecycleTransformer: LifecycleTransformer<BaseResponse<String>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun login(filedMap: Map<String, String>, lifecycleTransformer: LifecycleTransformer<BaseResponse<MemberLoginResponse>>) {

        mvpView.showLoading(true, LOGIN)
        RetrofitFactory.getInstance()
                .getService(LoginApiService::class.java)
                .login(filedMap)
                .map(ApiServerResultFunction<MemberLoginResponse>())
                .onErrorResumeNext(ExceptionFunction<MemberLoginResponse>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(lifecycleTransformer)
                .subscribe(object : HttpObserver<MemberLoginResponse>(LOGIN) {

                    override fun onFail(e: BaseException) {
                        mvpView.hideLoading()
                        handleApiException(e)
                        if (isTokenExpired) {
                            mvpView.openActivityOnTokenExpire()
                        } else {
                            mvpView.showToastMessage(e.msg)
                        }
                    }

                    override fun onSuccess(response: BaseResponse<MemberLoginResponse>) {
                        mvpView.hideLoading()
                        if (response.jsonBody != null) {
                            mvpView.setAccessToken(response.jsonBody.token!!)

                        }
//                        mvpView.openMainActivity()
                    }
                })


    }


}