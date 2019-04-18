package com.spring.cloud.auth.client.interceptor;

import com.spring.cloud.api.handler.BaseContextHandler;
import com.spring.cloud.auth.client.annotation.IgnoreUserToken;
import com.spring.cloud.auth.client.config.UserAuthConfig;
import com.spring.cloud.auth.client.jwt.UserAuthUtil;
import com.spring.cloud.auth.common.util.jwt.IJWTInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设置Base缓存
 *
 * @author wangmj
 * @since 2018/11/2
 */
public class UserAuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserAuthConfig userAuthConfig;

    @Autowired
    private UserAuthUtil userAuthUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        IgnoreUserToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
        }
        if (annotation != null) {
            return super.preHandle(request, response, handler);
        }
        String token = request.getHeader(userAuthConfig.getTokenHeader());

        IJWTInfo infoFromToken = userAuthUtil.getInfoFromToken(token);
        BaseContextHandler.setUsername(infoFromToken.getUniqueName());
        BaseContextHandler.setName(infoFromToken.getName());
        BaseContextHandler.setUserID(infoFromToken.getId());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
