package com.challenge.geolocation.cache;

import com.challenge.geolocation.entity.GeoLocationEntity;
import com.challenge.geolocation.interactor.GeoLocationFactory;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CacheConfigManager {

    private static CacheConfigManager cacheConfigManager = new CacheConfigManager();
    private LoadingCache<String, GeoLocationEntity> geoLocationCache;

    public static CacheConfigManager getInstance() {
        return cacheConfigManager;
    }

    public GeoLocationEntity initStudentCache(GeoLocationFactory factory, String ipAddress) {
        try {
            if (geoLocationCache == null) {
                geoLocationCache = CacheBuilder.newBuilder()
                        .concurrencyLevel(10)
                        .maximumSize(200) // Maximum of 200 records can be cached
                        .expireAfterAccess(60, TimeUnit.SECONDS) // Cache will expire after 30 minutes
                        .recordStats()
                        .build(new CacheLoader<>() { // Build the CacheLoader

                            @Override
                            public GeoLocationEntity load(String ipAddress) throws Exception {
                                log.info("Fetching geo location Data from DB/ Cache Miss");

                                return factory.make().execute(ipAddress);
                            }
                        });
            } else {
                geoLocationCache.getUnchecked(ipAddress);
                CacheStats cacheStats = geoLocationCache.stats();
                log.info("CacheStats = {} ", cacheStats);
                log.info("Geo Location Coming from cache for ipAddress: " + ipAddress);
                return geoLocationCache.get(ipAddress);
            }
        }catch (ExecutionException ex){
            log.error("Error Retrieving Elements from the geo location cache. Failed with error: " +
                    ex.getMessage());
        }
        return null;
    }
}
