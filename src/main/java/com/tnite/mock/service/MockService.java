package com.tnite.mock.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MockService {
	
    @Autowired
    private ResourceFileLoaderService resourceFileLoader;

    private static final String RESOURCE_FOLDER = "data";
    private static final String EMPTY_DATA_FILENAME = "empty";
    
    private static Logger logger = LoggerFactory.getLogger(MockService.class);

    public String getResponse(String data) {
        logger.info("Received data param:{}", data);
        String contents = resourceFileLoader.getTemplateFromFile(RESOURCE_FOLDER + "/" + data);
        logger.info("Received content from file {}: {}", data, contents);
        if (contents.isEmpty()) {
            logger.info("Could not find contents for {}. Returning empty data", data);
            return resourceFileLoader.getTemplateFromFile(RESOURCE_FOLDER + "/" + EMPTY_DATA_FILENAME);
        } else {
            logger.info("Content is {}, {}", contents, data);
            return contents;
        }
    }
	
    public Map<String, HttpStatus> getHttpStatus(int code) {
        Map<String, HttpStatus> status = new HashMap<String, HttpStatus>();
        status.put(HttpStatus.valueOf(code).toString(), HttpStatus.valueOf(code));
        return status;
    }

    public String getDelay(String time) throws InterruptedException {
        Long delay = Long.valueOf(time);
        Thread.sleep(delay);
        return "Response with delay of " + time + " milliseconds";
    }
}
