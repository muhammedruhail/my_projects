package com.ruhail.mechinetestapp.rest;

import java.net.URISyntaxException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruhail.mechinetestapp.domain.Media;
import com.ruhail.mechinetestapp.service.MediaService;

@RestController
@RequestMapping("/api")
public class MediaResource {

    private final Logger log = LoggerFactory.getLogger(MediaResource.class);

    private final MediaService mediaService;

    public MediaResource(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    /**
     * POST  /media : Create a new media.
     *
     * @param media the media to create
     * @return the ResponseEntity with status 201 (Created) and with body the new media, or with status 400 (Bad Request) if the student has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/media")
    public ResponseEntity<Media> createMedia(@RequestBody Media media) throws URISyntaxException {
        log.debug("REST request to save media : {}", media);
		
        // Commented for testing purpose, usually will check id is null or not, 
        //if it is null, the data is to persisted, otherwise will throw an exception, bcz, data is to be updated so call post operation,
		//
        /*
		 * if (media.getId() != null) { throw new
		 * URISyntaxException("A new student cannot already have an ID", ""); }
		 */
		 
        Media result = mediaService.save(media);
        return ResponseEntity.ok().body(result);
    }
    
    /**
     * GET  /media/:id : get the "id" media.
     *
     * @param id the id of the media to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the media, or with status 404 (Not Found)
     */
    @GetMapping("/media/{id}")
    public ResponseEntity<Media> getMedia(@PathVariable String id) {
        log.debug("REST request to get Media : {}", id);
        Optional<Media> media = mediaService.findOne(id);
        return ResponseEntity.ok(media.get());
    }
}
