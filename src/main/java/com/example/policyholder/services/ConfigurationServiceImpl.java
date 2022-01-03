package com.example.policyholder.services;

import com.example.policyholder.cache.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationServiceImpl implements IConfigurationService{

  @Autowired
  private CacheUtil cacheUtil;

  public <T> T getConfigValue(String cacheName, String key, Class<T> responseType) {
    T response = this.cacheUtil.getValueFromCache(cacheName, key, responseType);
    return response;
  }
}
