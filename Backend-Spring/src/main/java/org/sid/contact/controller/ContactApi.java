package org.sid.contact.controller;

import org.sid.contact.entities.Contact;
import org.sid.contact.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactApi {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactApi(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contact not found"));
    }

    @PostMapping
    public Contact addContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable long id, @RequestBody Contact updatedContact) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contact.setFirstname(updatedContact.getFirstname());
                    contact.setLastname(updatedContact.getLastname());
                    contact.setEmail(updatedContact.getEmail());
                    contact.setPhone(updatedContact.getPhone());
                    contact.setType(updatedContact.getType());
                    contact.setGender(updatedContact.getGender());
                    return contactRepository.save(contact);
                })
                .orElseThrow(() -> new IllegalArgumentException("Contact not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable long id) {
        contactRepository.deleteById(id);
    }
}
