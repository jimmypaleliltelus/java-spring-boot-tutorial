package com.example.imageupload.repository;

import com.example.imageupload.components.ImageMetaData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ImageMetadataServiceTest {

    @Autowired
    private ImageMetadataService imageMetadataService;

    @Test
    public void testFindAll() {
        List<ImageMetaData> items = imageMetadataService.findAll();
        assertEquals(3, items.size());
    }

    @Test
    public void saveNew() {
        imageMetadataService.save(new ImageMetaData(1234, "Test Image",
                "https://some-url.com/test-image.png"));
        List<ImageMetaData> items = imageMetadataService.findAll();
        assertEquals(4, items.size());
    }
}
