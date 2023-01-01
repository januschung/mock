package com.tnite.mock.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.util.IOUtils;


@Service
public class ResourceFileLoaderService {

    @Autowired
    ResourceLoader resourceLoader;

    public String getTemplateFromFile(String fileName) {
        InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
        return IOUtils.toString(input);
    }

}
