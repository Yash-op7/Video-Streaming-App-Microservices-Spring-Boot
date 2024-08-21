package com.yash_95.video_streaming_app.movie_catalog_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash_95.video_streaming_app.movie_catalog_service.model.MovieInfo;
import com.yash_95.video_streaming_app.movie_catalog_service.model.MovieInfoRepository;



@RestController
public class MovieInfoController {
    
    @Autowired
    private MovieInfoRepository repository;

    @PostMapping("/movie-info/save")
    public List<MovieInfo> saveAll(@RequestBody List<MovieInfo> movieInfoList) {
        return repository.saveAll(movieInfoList);
    }

    @GetMapping("/movie-info/list")
    public List<MovieInfo> getAll() {
        return repository.findAll();
    }    

    @GetMapping("movie-info/find-path-by-id/{id}")
    public String getMethodName(@PathVariable("id") Long id) {
        var videoInfoOptional = repository.findById(id);
        // return videoInfoOptional.map(MovieInfo::getPath).orElse(null);
        // OR
        return videoInfoOptional.map(movieInfo -> movieInfo.getPath()).orElse(null);
    }
    
    
}
