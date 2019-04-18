package com.spring.cloud.auth.server.service.impl;

import com.spring.cloud.api.entity.Client;
import com.spring.cloud.auth.server.bean.ClientInfo;
import com.spring.cloud.auth.server.service.AuthClientService;
import com.spring.cloud.auth.server.util.client.ClientTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ace on 2017/9/10.
 */
@Service
public class DBAuthClientService implements AuthClientService {
    @Autowired
    private ClientTokenUtil clientTokenUtil;


    @Override
    public String apply(String clientId, String secret) throws Exception {
        Client client = new Client();
        return clientTokenUtil.generateToken(new ClientInfo(client.getCode(),client.getName(),client.getId().toString()));
    }
}
