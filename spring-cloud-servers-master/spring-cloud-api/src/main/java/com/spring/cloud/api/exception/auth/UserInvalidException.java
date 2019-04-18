package com.spring.cloud.api.exception.auth;


import com.spring.cloud.api.constants.CommonConstants;
import com.spring.cloud.api.exception.BaseException;

/**
 * Created by ace on 2017/9/8.
 */
public class UserInvalidException extends BaseException {
    public UserInvalidException(String message) {
        super(message, CommonConstants.EX_USER_PASS_INVALID_CODE);
    }
}
