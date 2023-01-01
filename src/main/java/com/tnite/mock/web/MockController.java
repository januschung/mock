package com.tnite.mock.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tnite.mock.service.MockService;

@RestController
public class MockController {

    @Autowired
    MockService mockService;

    @GetMapping(path = "/resource", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getServe(@RequestParam String data) throws Exception {
        String responseBody = mockService.getResponse(data);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
    
    @GetMapping(path = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getStatus(@RequestParam String code) throws Exception {
        Map<String, HttpStatus> statusMap = mockService.getHttpStatus(code);
        Map.Entry<String, HttpStatus> status = statusMap.entrySet().iterator().next();
        return new ResponseEntity<>(status.getKey(), status.getValue());
    }
    
    @GetMapping(path = "/delay", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDelay(@RequestParam String ms) throws Exception {
        String responseBody = mockService.getDelay(ms);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
