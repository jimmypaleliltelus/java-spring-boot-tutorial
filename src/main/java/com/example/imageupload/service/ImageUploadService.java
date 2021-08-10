package com.example.imageupload.service;

import com.example.imageupload.repository.CloudUploadService;
import com.example.imageupload.repository.ImageMetadataService;
import com.example.imageupload.components.ImageMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageUploadService {

    @Autowired
    private ImageMetadataService imageMetadataService;
    @Autowired
    private CloudUploadService cloudUploadService;

    public String getSignedUrl() {
        return this.cloudUploadService.getSignedUrl();
    }

    public ImageMetaData getImageMetadataById(int id) {
        return this.imageMetadataService.findById(id).get();
    }

    public String getImageUrlById(int id) {
        return this.imageMetadataService.getById(id).getImageUrl();
    }

    public void saveNew(int id, String imageName, String imageUrl) {
        this.imageMetadataService.save(new ImageMetaData(id, imageName, imageUrl));
    }
}
