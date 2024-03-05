package com.tnite.mock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
    
    private static Stream<Arguments> paramsForTestGetHttpStatus() {
        return Stream.of(
            Arguments.of(200, "200 OK", HttpStatus.OK),
            Arguments.of(403, "403 FORBIDDEN", HttpStatus.FORBIDDEN),
            Arguments.of(404, "404 NOT_FOUND", HttpStatus.NOT_FOUND),
            Arguments.of(500, "500 INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
            Arguments.of(503, "503 SERVICE_UNAVAILABLE", HttpStatus.SERVICE_UNAVAILABLE),
            Arguments.of(504, "504 GATEWAY_TIMEOUT", HttpStatus.GATEWAY_TIMEOUT)
        );
    }
    
    @ParameterizedTest
    @MethodSource("paramsForTestGetHttpStatus")
    public void TestGetHttpStatus(int statusCode, String response, HttpStatus httpStatus) {
        Map<String, HttpStatus> statusMap = mockService.getHttpStatus(statusCode);
        Map.Entry<String, HttpStatus> status = statusMap.entrySet().iterator().next();
        assertEquals(response, status.getKey());
        assertEquals(httpStatus, status.getValue());
    }

    @Test
    public void TestGetDelayReturnDelayValue() throws InterruptedException {
        String response = mockService.getDelay("999");
        assert(response.contains("999"));
    }
}
