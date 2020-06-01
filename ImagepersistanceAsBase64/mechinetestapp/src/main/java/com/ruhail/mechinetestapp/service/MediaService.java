package com.ruhail.mechinetestapp.service;

import java.util.Optional;

import com.ruhail.mechinetestapp.domain.Media;

/**
 * Service Interface for managing media.
 */
public interface MediaService {
	
	/**
     * Save a media.
     *
     * @param media the entity to save
     * @return the persisted entity
     */
    Media save(Media media);
    
    /**
     * Get the "id" media.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Media> findOne(String id);

}
