package com.tnite.mock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;



@ExtendWith(MockitoExtension.class)
public class TestMockService {


    @Mock
    ResourceFileLoaderService resourceFileLoader;
    
    @InjectMocks
    MockService mockService;    

    @Test
    public void TestGetResponseReturnExpectedValue() {
    	when(resourceFileLoader.getTemplateFromFile(anyString())).thenReturn("whatever");
        String response = mockService.getResponse("abc");
        assert(response.contains("whatever"));
    }
    
    @Test
    public void TestGetHttpStatusWhenRequest200Return200() {
        Map<String, HttpStatus> statusMap = mockService.getHttpStatus(200);
        Map.Entry<String, HttpStatus> status = statusMap.entrySet().iterator().next();
        assertEquals("200 OK", status.getKey());
        assertEquals(HttpStatus.OK, status.getValue());
    }
    
    @Test
    public void TestGetHttpStatusWhenRequest403Return403() {
        Map<String, HttpStatus> statusMap = mockService.getHttpStatus(403);
        Map.Entry<String, HttpStatus> status = statusMap.entrySet().iterator().next();
        assertEquals("403 FORBIDDEN", status.getKey());
        assertEquals(HttpStatus.FORBIDDEN, status.getValue());
    }
    
    @Test
    public void TestGetHttpStatusWhenRequest404Return404() {
        Map<String, HttpStatus> statusMap = mockService.getHttpStatus(404);
        Map.Entry<String, HttpStatus> status = statusMap.entrySet().iterator().next();
        assertEquals("404 NOT_FOUND", status.getKey());
        assertEquals(HttpStatus.NOT_FOUND, status.getValue());
    }
    
    @Test
    public void TestGetHttpStatusWhenRequest500Return500() {
        Map<String, HttpStatus> statusMap = mockService.getHttpStatus(500);
        Map.Entry<String, HttpStatus> status = statusMap.entrySet().iterator().next();
        assertEquals("500 INTERNAL_SERVER_ERROR", status.getKey());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, status.getValue());
    }
    
    @Test
    public void TestGetHttpStatusWhenRequest503Return503() {
        Map<String, HttpStatus> statusMap = mockService.getHttpStatus(503);
        Map.Entry<String, HttpStatus> status = statusMap.entrySet().iterator().next();
        assertEquals("503 SERVICE_UNAVAILABLE", status.getKey());
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, status.getValue());
    }
    
    @Test
    public void TestGetHttpStatusWhenRequest504Return504() {
        Map<String, HttpStatus> statusMap = mockService.getHttpStatus(504);
        Map.Entry<String, HttpStatus> status = statusMap.entrySet().iterator().next();
        assertEquals("504 GATEWAY_TIMEOUT", status.getKey());
        assertEquals(HttpStatus.GATEWAY_TIMEOUT, status.getValue());
    }

    @Test
    public void TestGetDelayReturnDelayValue() throws InterruptedException {
        String response = mockService.getDelay("999");
        assert(response.contains("999"));
    }
}
