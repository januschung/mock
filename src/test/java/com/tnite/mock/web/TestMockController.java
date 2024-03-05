package com.tnite.mock.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.tnite.mock.service.MockService;
import com.tnite.mock.service.ResourceFileLoaderService;


@WebMvcTest(MockController.class)
public class TestMockController {
	
    @Autowired
    private MockMvc mockMvc;
	
    @MockBean
	private ResourceFileLoaderService resourceFileLoader;

    @MockBean
    private MockService mockService;
    
    @Test
    public void testThatWhenTheServiceReturnValueThenResponseReturnTheSame() throws Exception {
        when(mockService.getResponse("123")).thenReturn("whatever");
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/resource?data=123").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(200, result.getResponse().getStatus());
        assertEquals("whatever", result.getResponse().getContentAsString());
    }
    
    @Test
    public void testThatWhenStatusRequestWithCode200ThenResponseReturnTheSame() throws Exception {
        when(mockService.getHttpStatus(200)).thenReturn(Collections.singletonMap("OK", HttpStatus.OK));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/status?code=200").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(200, result.getResponse().getStatus());
        assertEquals("OK", result.getResponse().getContentAsString());
    }
    
    @Test
    public void testThatWhenDelayRequestWith100msThenResponseReturnTheSame() throws Exception {
        when(mockService.getDelay("100")).thenReturn("Delay with 100ms");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/delay?ms=100").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(200, result.getResponse().getStatus());
        assertEquals("Delay with 100ms", result.getResponse().getContentAsString());
    }
}
