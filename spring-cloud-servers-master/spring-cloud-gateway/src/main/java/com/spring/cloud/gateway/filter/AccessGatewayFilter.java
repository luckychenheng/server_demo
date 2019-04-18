package com.spring.cloud.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.spring.cloud.api.message.BaseResponse;
import com.spring.cloud.api.message.TokenForbiddenResponse;
import com.spring.cloud.auth.client.EnableAceAuthClient;
import com.spring.cloud.auth.client.config.UserAuthConfig;
import com.spring.cloud.auth.client.jwt.UserAuthUtil;
import com.spring.cloud.auth.common.util.jwt.IJWTInfo;
import com.spring.cloud.gateway.feign.AuthServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author wangmj
 * @since 2018/10/25
 */
@Configuration
@EnableAceAuthClient
@Slf4j
public class AccessGatewayFilter implements GlobalFilter {

    private static final String GATE_WAY_PREFIX = "/api";
    @Value("${jwt.refreshMinSecond}")
    private int refreshMinSecond;
    @Value("${gate.ignore.startWith}")
    private String ignoreStartWith;

    private final UserAuthConfig userAuthConfig;
    private final UserAuthUtil userAuthUtil;
    private final AuthServiceFeign authServiceFeign;

    @Autowired
    public AccessGatewayFilter(UserAuthConfig userAuthConfig, UserAuthUtil userAuthUtil, AuthServiceFeign authServiceFeign) {
        this.userAuthConfig = userAuthConfig;
        this.userAuthUtil = userAuthUtil;
        this.authServiceFeign = authServiceFeign;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("check token and user permission....");
        LinkedHashSet requiredAttribute = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        ServerHttpRequest request = exchange.getRequest();
        String requestUri = request.getPath().pathWithinApplication().value();
        if (requiredAttribute != null) {
            Iterator<URI> iterator = requiredAttribute.iterator();
            while (iterator.hasNext()) {
                URI next = iterator.next();
                if (next.getPath().startsWith(GATE_WAY_PREFIX)) {
                    requestUri = next.getPath().substring(GATE_WAY_PREFIX.length());
                }
            }
        }

        final String method = request.getMethod().toString();
        ServerHttpRequest.Builder mutate = request.mutate();
        if (isStartWith(requestUri)) {
            ServerHttpRequest build = mutate.build();
            return chain.filter(exchange.mutate().request(build).build());
        }
        IJWTInfo user = null;
        try {
            user = getJWTUser(request, mutate);
        }  catch (Exception e) {
            log.error("用户Token过期异常", e);
            return getVoidMono(exchange, new TokenForbiddenResponse("用户Token异常"));
        }
        ServerHttpRequest build = mutate.build();
        return chain.filter(exchange.mutate().request(build).build());
    }

    /**
     * 网关抛异常
     *
     * @param body
     */
    @NotNull
    private Mono<Void> getVoidMono(ServerWebExchange serverWebExchange, BaseResponse body) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        byte[] bytes = JSONObject.toJSONString(body).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : ignoreStartWith.split(",")) {
            if (requestUri.startsWith(s.trim())) {
                return true;
            }
        }
        return flag;
    }

    /**
     * 返回session中的用户信息
     *
     * @param request
     * @param ctx
     * @return
     */
    private IJWTInfo getJWTUser(ServerHttpRequest request, ServerHttpRequest.Builder ctx) throws Exception {
        List<String> strings = request.getHeaders().get(userAuthConfig.getTokenHeader());
        String authToken = null;
        if (strings != null) {
            authToken = strings.get(0);
        }
        if (StringUtils.isEmpty(authToken)) {
            strings = request.getQueryParams().get("token");
            if (strings != null) {
                authToken = strings.get(0);
            }
        }
        IJWTInfo ijwtInfo = userAuthUtil.getInfoFromToken(authToken);
        int exp = ijwtInfo.getExp();
        if (!StringUtils.isEmpty(exp)) {
            long differenceMillis = Long.valueOf(exp) * 1000L - DateTime.now().getMillis();
            long refreshMinMillis = Integer.valueOf(refreshMinSecond).longValue() * 1000L;
            if (0 < differenceMillis && differenceMillis < refreshMinMillis) {
                String refreshToken = authServiceFeign.refresh(authToken);
                authToken = refreshToken;
            }
        }
        ctx.header(userAuthConfig.getTokenHeader(), authToken);
        return ijwtInfo;
    }
}
