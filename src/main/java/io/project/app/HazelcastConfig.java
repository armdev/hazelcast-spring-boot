package io.project.app;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import static java.util.Collections.singletonList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public Config config() {
        Config config = new Config();
        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getTcpIpConfig().setEnabled(true).setMembers(singletonList("127.0.0.1"));
        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();
        config.setProperty("hazelcast.jmx", "true");
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
