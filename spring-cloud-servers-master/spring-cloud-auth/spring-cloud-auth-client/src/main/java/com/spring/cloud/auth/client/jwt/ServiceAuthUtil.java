
package com.spring.cloud.auth.client.jwt;

import com.spring.cloud.auth.client.config.ServiceAuthConfig;
import com.spring.cloud.auth.client.exception.JwtIllegalArgumentException;
import com.spring.cloud.auth.client.exception.JwtSignatureException;
import com.spring.cloud.auth.client.exception.JwtTokenExpiredException;
import com.spring.cloud.auth.common.util.jwt.IJWTInfo;
import com.spring.cloud.auth.common.util.jwt.JWTHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ace on 2017/9/15.
 */
@Slf4j
@Configuration
public class ServiceAuthUtil {
    @Autowired
    private ServiceAuthConfig serviceAuthConfig;

    public IJWTInfo getInfoFromToken(String token) throws Exception {
        try {
            return JWTHelper.getInfoFromToken(token, serviceAuthConfig.getPubKeyPath());
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenExpiredException("Client token expired!");
        } catch (SignatureException ex) {
            throw new JwtSignatureException("Client token signature error!");
        } catch (IllegalArgumentException ex) {
            throw new JwtIllegalArgumentException("Client token is null or empty!");
        }
    }

}