package com.tiger.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.MultipartConfigElement;

/**
 * Created by wangshaohu on 10/9/18.
 */
@Configuration
public class UploadFileConfiguration {

    private String fileSizeThreshold = "100KB"; //100kb
    private String maxFileSize = "100MB"; //100MB

    public String getFileSizeThreshold() {
        return fileSizeThreshold;
    }

    public void setFileSizeThreshold(String fileSizeThreshold) {
        this.fileSizeThreshold = fileSizeThreshold;
    }

    public String getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
        multipartConfigFactory.setFileSizeThreshold(this.fileSizeThreshold);
        multipartConfigFactory.setMaxFileSize(this.maxFileSize);
        return multipartConfigFactory.createMultipartConfig();
    }

}
