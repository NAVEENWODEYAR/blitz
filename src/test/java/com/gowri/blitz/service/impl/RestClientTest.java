package com.gowri.blitz.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/*
 * @author NaveenWodeyar
 * @date 06-05-2025
 */

public class RestClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RestClient restClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGet() {
        String url = "http://example.com/api/data";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer token");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer token");
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        String expectedResponse = "{\"message\":\"success\"}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(expectedResponse, HttpStatus.OK);

        when(restTemplate.exchange(url, HttpMethod.GET, entity, String.class)).thenReturn(responseEntity);

        String actualResponse = restClient.get(url, headers);

        assertEquals(expectedResponse, actualResponse);
        verify(restTemplate, times(1)).exchange(url, HttpMethod.GET, entity, String.class);
    }
}
