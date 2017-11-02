package io.project.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import io.project.model.Contact;
import io.project.jparepositories.ContactRepository;
import javax.persistence.Cacheable;
import org.springframework.stereotype.Repository;

@Repository("contactService")
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private HazelcastService hazelcastService;

    @Override
    public Contact save(Contact contact) {
        Contact retContact = contactRepository.save(contact);
        if (retContact != null) {
            System.out.println("Storing new contact to the Cache");
            hazelcastService.add(retContact);
        }
        return retContact;
    }

    @Override
   
    public List<Contact> findAll() {
        List<Contact> contactList = hazelcastService.list();
        System.out.println("Find list in the cache!!!!!!");         

        if (contactList == null || contactList.isEmpty()) {
            System.out.println("Fetch Contact list from Database");
            contactList = contactRepository.findAll();
            hazelcastService.addList(contactList);
            return contactList;
        }

        if (!contactList.isEmpty()) {
            System.out.println("Fetch Contact list from Cache");
            return contactList;
        }

        return null;
    }

    @Override
    public Contact findOne(Long id) {

        Contact contact = hazelcastService.get(id);
        if (contact == null) {
            System.out.println("User with id " + id + " does not exist in the cache");
            System.out.println("Get from DB");
            contact = contactRepository.findOne(id);
            if (contact != null) {
                hazelcastService.add(contact);
            }
        }

        if (contact != null) {
            System.out.println("User with id " + id + " in the cache");
            System.out.println("Get from cache");
        }

        return contact;
    }

}
