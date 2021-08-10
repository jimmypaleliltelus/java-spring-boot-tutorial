package com.example.imageupload.controller;

import com.example.imageupload.service.ImageUploadService;
import com.example.imageupload.components.ImageMetaData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImageUploadController.class)
public class ImageUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageUploadService imageUploadService;

    @Test
    public void getImageMetadataById() throws Exception {
        when(imageUploadService.getImageMetadataById(anyInt()))
                .thenReturn(new ImageMetaData(1234, "Test Image", "https://someurl.com/asdfads.jpg"));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/get-image-metadata-by-id?id=1234")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1234, \"name\":\"Test Image\", " +
                        "\"imageUrl\":\"https://someurl.com/asdfads.jpg\"}"))
                .andReturn();
    }

    @Test
    public void getImageUrlById() throws Exception {
        when(imageUploadService.getImageUrlById(anyInt()))
                .thenReturn("https://someurl.com/asdfads.jpg");

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/get-image-url-by-id?id=10001")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("https://someurl.com/asdfads.jpg"))
                .andReturn();
    }
}
