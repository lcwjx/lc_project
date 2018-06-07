package com.lee.login

import com.framework.http.model.BaseResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by lichen on 2018/6/6.
 */
interface LoginApiService {
    /**
     * 手机号&验证码登录.
     *
     * @param fieldMap the field map
     * @return 登录信息
     */
    @POST("base-gateway")
    fun login(@Body fieldMap: RequestBody): Observable<BaseResponse<MemberLoginResponse>>
}