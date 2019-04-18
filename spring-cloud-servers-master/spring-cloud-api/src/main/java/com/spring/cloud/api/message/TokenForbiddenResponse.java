package com.spring.cloud.api.message;

import com.spring.cloud.api.constants.RestCodeConstants;

/**
 * @author wangmj
 * @since 2018/10/30
 */
public class TokenForbiddenResponse extends BaseResponse {
    public TokenForbiddenResponse(String message) {
        super(RestCodeConstants.TOKEN_ERROR_CODE, message);
    }
}
