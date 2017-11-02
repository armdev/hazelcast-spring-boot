package io.project.services;

import io.project.model.Contact;
import java.util.List;

public interface ContactService {

    List<Contact> findAll();

    Contact findOne(Long id);
    
    Contact save(Contact contact);

}
