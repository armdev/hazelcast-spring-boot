package io.project.services;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;
import com.hazelcast.core.IMap;
import io.project.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("hazelcastService")
public class HazelcastServiceImpl implements HazelcastService {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    public HazelcastServiceImpl() {
    }

    @Override
    public Contact get(Long id) {
        IMap<Long, Contact> dataStore = hazelcastInstance.getMap("contactMap");
        return dataStore.get(id);
    }

    @Override
    public void remove(Long id) {
        IMap<Long, Contact> dataStore = hazelcastInstance.getMap("contactMap");
        dataStore.remove(id);
    }

    @Override
    public void add(Contact entity) {
        IMap<Long, Contact> dataStore = hazelcastInstance.getMap("contactMap");
        dataStore.put(entity.getId(), entity);
    }

    @Override
    public List<Contact> list() {
        IList<Contact> dataStore = hazelcastInstance.getList("contactList");
        return dataStore;
    }

    @Override
    public void addList(List<Contact> list) {
        IList<Contact> dataStore = hazelcastInstance.getList("contactList");
        dataStore.addAll(list);
    }

    @Override
    public void removeList() {
        IList<Contact> dataStore = hazelcastInstance.getList("contactList");
        dataStore.clear();
    }

    public void shutDown() {
        hazelcastInstance.getLifecycleService().shutdown();
    }
}
