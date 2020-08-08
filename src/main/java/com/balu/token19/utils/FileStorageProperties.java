package com.balu.token19.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "file")
@Component
public class FileStorageProperties {
    private String uploadDir;
}