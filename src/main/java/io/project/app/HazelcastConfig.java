package io.project.app;

import com.hazelcast.config.Config;
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
        listConfig.setBackupCount(1);
        listConfig.setName("contactList");
        config.addListConfig(listConfig);
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
        return instance;
    }
}
