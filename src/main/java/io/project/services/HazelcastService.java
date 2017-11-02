package io.project.services;


import io.project.model.Contact;
import java.util.List;

/**
 *
 * @author Home
 */
public interface HazelcastService {

    public Contact get(Long id);
    public void remove(Long id);
    public void add(Contact entity);
    public List<Contact> list();
    public void addList(List<Contact> list);
    public void removeList() ;
    

}
