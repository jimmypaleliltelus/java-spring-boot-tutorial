package com.example.imageupload.controller;

import com.example.imageupload.service.ImageUploadService;
import com.example.imageupload.components.ImageMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    @GetMapping("/get-image-metadata-by-id")
    public ImageMetaData getImageMetadataById(@RequestParam(name = "id") int id) {
        return this.imageUploadService.getImageMetadataById(id);
    }

    @GetMapping("/get-image-url-by-id")
    public String getImageUrlById(@RequestParam(name = "id") int id) {
        return this.imageUploadService.getImageUrlById(id);
    }


}
