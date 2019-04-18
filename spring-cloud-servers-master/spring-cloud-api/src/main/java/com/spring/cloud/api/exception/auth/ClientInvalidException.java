package com.spring.cloud.api.exception.auth;


import com.spring.cloud.api.constants.CommonConstants;
import com.spring.cloud.api.exception.BaseException;

/**
 * Created by ace on 2017/9/10.
 */
public class ClientInvalidException extends BaseException {
    public ClientInvalidException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
