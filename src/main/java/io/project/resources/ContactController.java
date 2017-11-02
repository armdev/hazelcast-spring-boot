package io.project.resources;

import io.project.model.Contact;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.project.services.ContactService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
public class ContactController {

    private static final Logger log = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @GetMapping(path = "/all", produces = "application/json;charset=UTF-8")
    public List<Contact> findAll() {
        log.debug("REST request to get all Blogs");
        List<Contact> flightList = contactService.findAll();
        return flightList;
    }

    @GetMapping(path = "/one/{id}", produces = "application/json;charset=UTF-8")
    public Contact findOne(@PathVariable Long id) {
        log.debug("REST request to get one");
        Contact model = contactService.findOne(id);
        return model;
    }

    @PostMapping(path = "/save", produces = "application/json;charset=UTF-8")
    public Contact save(Contact contact) {
        log.debug("REST request to get one");
        Contact model = contactService.save(contact);
        return model;
    }

}
