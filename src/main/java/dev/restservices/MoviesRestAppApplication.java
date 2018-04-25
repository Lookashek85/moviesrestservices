package dev.restservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
@EnableCaching
public class MoviesRestAppApplication  {
	
	    @Bean
	    public EhCacheManagerFactoryBean getEhCacheFactory(){
	        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
	        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml") );
	        factoryBean.setShared(true);
	        return factoryBean;
	        }
	  
		Logger log = LoggerFactory.getLogger(this.getClass().getName());
		
		public static void main(String[] args) {
			SpringApplication.run(MoviesRestAppApplication.class, args);
		}
	  
}
