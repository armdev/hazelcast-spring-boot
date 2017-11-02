package io.project.app;

import com.hazelcast.config.ListConfig;
import static com.hazelcast.config.MapConfig.DEFAULT_TTL_SECONDS;

public class LocalListConfig extends ListConfig {

    private int timeToLiveSeconds = DEFAULT_TTL_SECONDS;

    public LocalListConfig() {
    }

    public LocalListConfig(LocalListConfig config) {
        this.timeToLiveSeconds = config.getTimeToLiveSeconds();
    }

    public int getTimeToLiveSeconds() {
        return timeToLiveSeconds;
    }

    public LocalListConfig setTimeToLiveSeconds(int timeToLiveSeconds) {
        this.timeToLiveSeconds = timeToLiveSeconds;
        return this;
    }

}
