package com.example.imageupload.repository;

import com.example.imageupload.components.ImageMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageMetadataService extends JpaRepository<ImageMetaData, Integer> {

}
