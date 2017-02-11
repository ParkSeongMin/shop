package com.tm.shop.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tm.shop.entity.Shop;

/**
 * shop repository
 * with paging and sorting
 * 
 * @author Park SeongMin
 */
@RepositoryRestResource(collectionResourceRel = "shop", path = "shop")
public interface ShopRepository extends PagingAndSortingRepository<Shop, Long> {

}
