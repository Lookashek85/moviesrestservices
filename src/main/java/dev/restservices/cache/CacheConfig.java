package dev.restservices.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/*@Configuration
@EnableCaching*/
public class CacheConfig {
	
	

	/*@Autowired
    private CacheManager cacheManager;
	
	@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("movies");
    }	
	 @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        
        cacheManager.setCaches(Arrays.asList(
          new ConcurrentMapCache("movies"))); 
          
        return cacheManager;
    }*/
	 

}
