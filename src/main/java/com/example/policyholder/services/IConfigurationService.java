package com.example.policyholder.services;

public interface IConfigurationService {
  <T> T getConfigValue(String cacheName, String key, Class<T> responseType);
}
