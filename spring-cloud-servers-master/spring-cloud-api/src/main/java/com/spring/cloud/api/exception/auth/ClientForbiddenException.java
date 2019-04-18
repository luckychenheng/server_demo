package com.spring.cloud.api.exception.auth;


import com.spring.cloud.api.constants.CommonConstants;
import com.spring.cloud.api.exception.BaseException;

/**
 * Created by ace on 2017/9/12.
 */
public class ClientForbiddenException extends BaseException {
    public ClientForbiddenException(String message) {
        super(message, CommonConstants.EX_CLIENT_FORBIDDEN_CODE);
    }

}
