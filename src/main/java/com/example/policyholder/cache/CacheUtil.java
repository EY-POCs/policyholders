package com.example.policyholder.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

public class CacheUtil {

  @Autowired
  private CacheManager cacheManager;

  /**
   * API to get value from Cache
   *
   * @param cacheName    - Name of the Cache
   * @param key          _ Key to fetch from Cache
   * @param responseType - Type of response expected
   * @param <T>          Response Type
   * @return Value defined in response type, null if value not available in cache
   */
  public <T> T getValueFromCache(String cacheName, String key, Class<T> responseType) {
    return cacheManager.getCache(cacheName).get(key, responseType);
  }
}
