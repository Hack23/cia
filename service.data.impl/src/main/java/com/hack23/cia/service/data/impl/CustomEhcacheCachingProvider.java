/*
 * Copyright 2010 James Pether Sörling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id$
 *  $HeadURL$
*/
package com.hack23.cia.service.data.impl;

import java.net.URI;

import javax.cache.CacheManager;

import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.cache.jcache.JCacheCacheManager;

/**
 * The Class CustomEhcacheCachingProvider.
 */
public final class CustomEhcacheCachingProvider extends EhcacheCachingProvider {

	/** The cache manager. */
	private static JCacheCacheManager cacheManager;

	@Override
	public CacheManager getCacheManager(final URI uri, final ClassLoader classLoader) {
		return cacheManager.getCacheManager();
	}

	/**
	 * Sets the cache manager.
	 *
	 * @param cacheManager the new cache manager
	 */
	public void setCacheManager(JCacheCacheManager cacheManager) {
		CustomEhcacheCachingProvider.cacheManager = cacheManager;
	}

}
