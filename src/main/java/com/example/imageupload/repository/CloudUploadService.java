package com.example.imageupload.repository;

import org.springframework.stereotype.Component;

@Component
public interface CloudUploadService {

    String getSignedUrl();
}
