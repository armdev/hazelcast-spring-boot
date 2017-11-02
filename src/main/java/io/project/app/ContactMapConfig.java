package io.project.app;

import com.hazelcast.config.MapConfig;
import static com.hazelcast.config.MapConfig.DEFAULT_TTL_SECONDS;

public class ContactMapConfig extends MapConfig {

    private int timeToLiveSeconds = DEFAULT_TTL_SECONDS;

    public ContactMapConfig() {
    }

    public ContactMapConfig(ContactMapConfig config) {
        this.timeToLiveSeconds = config.getTimeToLiveSeconds();
    }

    @Override
    public int getTimeToLiveSeconds() {
        return timeToLiveSeconds;
    }

    /**
     *
     * @param timeToLiveSeconds
     * @return
     */
    public ContactMapConfig setTimeToLiveSeconds(int timeToLiveSeconds) {
        this.timeToLiveSeconds = timeToLiveSeconds;
        return this;
    }

}
