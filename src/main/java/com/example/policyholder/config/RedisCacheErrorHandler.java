package com.example.policyholder.config;


import io.lettuce.core.RedisCommandTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
public class RedisCacheErrorHandler implements CacheErrorHandler {

  Logger baseLogger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
    handleTimeOutException(exception);
    baseLogger.debug("Unable to get from cache " + cache.getName() + " : " + exception.getMessage());
  }

  @Override
  public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
    handleTimeOutException(exception);
    baseLogger.debug("Unable to put into cache " + cache.getName() + " : " + exception.getMessage());
  }

  @Override
  public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
    handleTimeOutException(exception);
    baseLogger.debug("Unable to evict from cache " + cache.getName() + " : " + exception.getMessage());
  }

  @Override
  public void handleCacheClearError(RuntimeException exception, Cache cache) {
    handleTimeOutException(exception);
    baseLogger.debug("Unable to clean cache " + cache.getName() + " : " + exception.getMessage());
  }

  /**
   * for any redis exception or a cache miss get the data from actual storage
   *
   * @param exception
   */
  private void handleTimeOutException(RuntimeException exception) {

    if (exception instanceof RedisCommandTimeoutException)
      return;
  }
}
