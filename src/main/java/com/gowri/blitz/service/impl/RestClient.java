package com.gowri.blitz.service.impl;

/*
 * @author NaveenWodeyar
 * @date 11-04-2025
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestClient {

    private static final Logger log = LoggerFactory.getLogger(RestClient.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    public String get(String url, Map<String, String> headers){

        String result="";

        return result;
    }
}
