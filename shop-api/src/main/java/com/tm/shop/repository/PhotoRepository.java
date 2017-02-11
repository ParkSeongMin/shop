package com.tm.shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tm.shop.entity.Photo;

/**
 * photo repository
 * 
 * @author Park SeongMin
 */
@RepositoryRestResource(collectionResourceRel="photo", itemResourceRel="photo")
public interface PhotoRepository extends CrudRepository<Photo, Long> {

}
