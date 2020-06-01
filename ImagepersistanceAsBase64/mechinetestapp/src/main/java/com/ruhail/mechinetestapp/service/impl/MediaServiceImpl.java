package com.ruhail.mechinetestapp.service.impl;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruhail.mechinetestapp.domain.Media;
import com.ruhail.mechinetestapp.repository.MediaRepository;
import com.ruhail.mechinetestapp.service.MediaService;

/**
 * Service Interface for managing media.
 */
@Service
@Transactional
public class MediaServiceImpl implements MediaService{
	
	private final Logger log = LoggerFactory.getLogger(MediaServiceImpl.class);

    private final MediaRepository mediaRepository;
   
    public MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

	/**
     * Save a media.
     *
     * @param media the entity to save
     * @return the persisted entity
     */
	@Override
	public Media save(Media media) {
		log.debug("Request to save Media : {}", media);
		//ByteArrayOutputStream instance to stream into
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		BufferedImage image =null;
        try{
 
           URL url =new URL(media.getUrl());
            // read the url
           image = ImageIO.read(url);
           ImageIO.write(image, "jpg", bos);
           byte[] imageBytes = bos.toByteArray();
           
           //Returns A newly-allocated byte array containing the resulting encoded bytes.
           byte[] bytes=Base64.getEncoder().encode(imageBytes);
           
           //byte array to string object
           String base64String=new String(bytes);
           log.info("Request to save Media : {}", base64String);
           bos.close();
        
           media.setImage(bytes);
           
        }catch(IOException e){
            e.printStackTrace();
        }
		return mediaRepository.save(media);
	}

	/**
     * Get the "id" media.
     *
     * @param id the id of the entity
     * @return the entity
     */
	@Override
	public Optional<Media> findOne(String id) {
		log.debug("Request to get Media  by id: {}", id);
		return mediaRepository.findById(id);
	}

}
