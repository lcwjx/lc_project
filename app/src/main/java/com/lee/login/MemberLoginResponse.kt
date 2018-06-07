package com.lee.login

/**
 * Created by lichen on 2017/11/6.
 */
class MemberLoginResponse(var token: String? = null, var pwdErrNum: Int = 0) {

    override fun toString(): String {
        return "MemberLoginResponse{" +
                "token='" + token + '\'' +
                ", pwdErrNum=" + pwdErrNum +
                '}'
    }

}
