package io.project.app;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();
        LocalListConfig listConfig = new LocalListConfig();
        listConfig.setMaxSize(50);
        listConfig.setTimeToLiveSeconds(10);
        listConfig.setBackupCount(0);
        listConfig.setName("contactList");
        
        config.addListConfig(listConfig);

        ContactMapConfig contactMapConfig = new ContactMapConfig();
        
        contactMapConfig.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE));
        contactMapConfig.setTimeToLiveSeconds(10);
        contactMapConfig.setBackupCount(0);
        contactMapConfig.setName("contactMap");
        contactMapConfig.setEvictionPolicy(EvictionPolicy.LRU);
        contactMapConfig.setEvictionPercentage(25);
        contactMapConfig.setInMemoryFormat(InMemoryFormat.BINARY);
        contactMapConfig.setMergePolicy("com.hazelcast.map.merge.PassThroughMergePolicy");

        config.addMapConfig(contactMapConfig);

        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
        
        return instance;
    }
}


///https://memorynotfound.com/spring-boot-hazelcast-caching-example-configuration/