package com.yash_95.video_streaming_app.movie_streaming_service.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MovieStreamController {

    public static final String VIDEO_DIRECTORY = "/Users/yashmeena/Desktop/stuff/git repos/Video-Streaming-App-Microservices-Spring-Boot/assets/";

    @GetMapping("/stream/{videoPath}")
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable("videoPath") String videoPath) throws FileNotFoundException  {
        System.out.println("video path is" + VIDEO_DIRECTORY + videoPath);

        File file = new File(VIDEO_DIRECTORY+videoPath);
        if(file.exists()) {
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("video/mp4"))
                .body(inputStreamResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
