package com.example.imageupload.service;

import com.example.imageupload.repository.CloudUploadService;
import com.example.imageupload.repository.ImageMetadataService;
import com.example.imageupload.components.ImageMetaData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImageUploadServiceTest {

    @InjectMocks
    private ImageUploadService imageUploadService;

    @Mock
    private ImageMetadataService imageMetadataService;
    @Mock
    CloudUploadService cloudUploadService;

    @Test
    public void getSignedUrl() {
        when(cloudUploadService.getSignedUrl()).thenReturn("someurl");
        assertEquals("someurl", imageUploadService.getSignedUrl());
    }

    @Test
    public void getByMetadataId() {
        when(imageMetadataService.findById(anyInt()))
                .thenReturn(java.util.Optional.of(new ImageMetaData(10001, "Image1", "someurl")));
        ImageMetaData res = imageUploadService.getImageMetadataById(10001);
        assertEquals("someurl", res.getImageUrl());
        assertEquals(10001, res.getId());
        assertEquals("Image1", res.getName());
    }

    @Test
    public void getUrl() {
        when(imageMetadataService.getById(anyInt())).thenReturn(
                new ImageMetaData(10001, "test Image", "https://testurl.com/10001-image.png"));

        String uploadUrl = imageUploadService.getImageUrlById(10001);
        assertEquals("https://testurl.com/10001-image.png", uploadUrl);
    }

    @Test
    public void saveNew() {
        imageUploadService.saveNew(1235, "Test Image", "https://some-url.com/image.jpg");
        ArgumentCaptor<ImageMetaData> captor = ArgumentCaptor.forClass(ImageMetaData.class);
        verify(imageMetadataService).save(captor.capture());
        assertEquals(1235, captor.getValue().getId());
    }
}
