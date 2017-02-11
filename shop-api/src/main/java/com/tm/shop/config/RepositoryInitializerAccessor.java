package com.tm.shop.config;

/**
 * RepositoryInitializer에 접근 가능한 메서드를 제공한다.
 * 
 * @author Park SeongMin
 * @see RepositoryInitializer
 *
 */
public abstract class RepositoryInitializerAccessor {

	public static void clear(RepositoryInitializer initializer) {
		initializer.clear();
	}
	
}
