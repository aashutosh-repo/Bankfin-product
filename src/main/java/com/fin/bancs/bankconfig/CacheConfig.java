package com.fin.bancs.bankconfig;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
public class CacheConfig {

    private final CacheProperties cacheProperties;

    public CacheConfig(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    @Bean
    public CacheManager cacheManager() {
        //Update this list for new cache
    	List<String> allRecords= Arrays.asList("customer","events","shipments", "trackingEvents");
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheNames(allRecords);
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(1000) //Max cache size can be 1000 - change accordingly
                .expireAfterWrite(10, TimeUnit.MINUTES)); //once create cache will be valid for next 10 min
        return cacheManager;
    }
}
