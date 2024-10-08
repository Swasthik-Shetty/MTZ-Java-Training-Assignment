package com.monetize360.cbook.dao;

import com.monetize360.cbook.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {

    List<Contact> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrMobileContainingIgnoreCase(
            String firstName, String lastName, String email, String mobile);
}
