package io.project.jparepositories;


import io.project.model.Contact;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface ContactRepository extends CrudRepository<Contact, Long> {    
    
    @Override
    List<Contact> findAll();
    
    @Override
    Contact findOne(Long id);    
    
}
