package com.yash_95.video_streaming_app.movie_streaming_service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieCatalogService {

    public static final String CATALOG_SERVICE = "http://movie-catalog-service";
    
    @Autowired
    private RestTemplate restTemplate;

    public String getMoviePath(Long id) {
        restTemplate.getForEntity(Catalog);
    }
}
