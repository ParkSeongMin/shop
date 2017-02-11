package com.tm.shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tm.shop.entity.ValueDetail;

/**
 * value detail repository
 * 
 * @author Park SeongMin
 *
 */
@RepositoryRestResource(collectionResourceRel="value-detail", itemResourceRel="value-detail")
public interface ValueDetailRepository extends CrudRepository<ValueDetail, Long>{

}
