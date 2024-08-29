package com.monetize360.contactapp.dao;

import com.monetize360.contactapp.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {

}
