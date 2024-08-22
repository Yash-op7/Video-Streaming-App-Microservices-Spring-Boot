package com.yash_95.video_streaming_app.movie_streaming_service.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yash_95.video_streaming_app.movie_streaming_service.MovieCatalogService;


@RestController
public class MovieStreamController {
    
    @Autowired
    private MovieCatalogService movieCatalogService;

    public static final Logger log = Logger.getLogger(MovieStreamController.class.getName());
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
    @GetMapping("/stream/with-id/{videoInfoId}")
    public ResponseEntity<InputStreamResource> streamVideoById(@PathVariable("videoInfoId") Long videoInfoId) throws FileNotFoundException  {
        // here we do interservice communication and exchange the id for the path
        String path = movieCatalogService.getMoviePath(videoInfoId);
        log.log(Level.INFO, "Resolved movie path by id through interservice communication with id = ", videoInfoId + " with path " +  path);
        return streamVideo(path);
    }

}
