package com.tm.shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tm.shop.entity.Value;

/**
 * value repository
 * 
 * @author Park SeongMin
 */
@RepositoryRestResource(collectionResourceRel="value", itemResourceRel="value")
public interface ValueRepository extends CrudRepository<Value, Long>{

}
