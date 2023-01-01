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
	ResourceFileLoaderService resourceFileLoader;

    private static Logger logger = LoggerFactory.getLogger(MockService.class);

    private static final String RESOURCE_FOLDER = "data";
    private static final String EMPTY_DATA_FILENAME = "empty";

    public String getResponse(String foo) {
        logger.info("Received foo:{}", foo);
        String contents = resourceFileLoader.getTemplateFromFile(RESOURCE_FOLDER + "/" + foo);
        logger.info("Received content from file {}: {}", foo, contents);
        if (contents.isEmpty()) {
            logger.info("Could not find contents for {}. Returning empty data", foo);
            return resourceFileLoader.getTemplateFromFile(RESOURCE_FOLDER + "/" + EMPTY_DATA_FILENAME);
        } else {
        	logger.info("Content is {}, {}", contents, foo);
            return contents;
        }
    }

	public Map<String, HttpStatus> getHttpStatus(String code) {
		Map<String, HttpStatus> status = new HashMap<String, HttpStatus>();
		switch (code) {
			case "200":
				status.put("OK", HttpStatus.OK);
				break;
			case "403":
				status.put("FORBIDDEN", HttpStatus.FORBIDDEN);
				break;
			case "404":
				status.put("NOT FOUND", HttpStatus.NOT_FOUND);
				break;
			case "500":
				status.put("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
				break;
			case "503":
				status.put("SERVICE UNAVAILABLE", HttpStatus.SERVICE_UNAVAILABLE);
				break;
			case "504":
				status.put("GATEWAY TIMEOUT", HttpStatus.GATEWAY_TIMEOUT);
				break;
			default:
				status.put("OK", HttpStatus.OK);
				break;
		}
		return status;
	}

	public String getDelay(String time) throws InterruptedException {
		Long delay = Long.valueOf(time);
		Thread.sleep(delay);
		return "Reponse with delay of " + time + " milliseconds";
	}
}
