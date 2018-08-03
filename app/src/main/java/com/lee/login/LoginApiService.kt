package com.lee.login

import com.framework.http.model.BaseResponse
import io.reactivex.Observable
import retrofit2.http.*

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
    @FormUrlEncoded
    @POST("base-gateway")
    fun login(@FieldMap fieldMap: Map<String, String>): Observable<BaseResponse<MemberLoginResponse>>

}