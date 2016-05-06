package ru.reksoft.lab.dao;

import ru.reksoft.lab.domain.Contact;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by mishanin on 27.04.2016.
 */
public interface ContactDao {

    String TABLE_NAME = "contacts_table";

    void addContact(String name, String surname, String telNumber, String mail, String organization, String position)
            throws SQLException;

    void deleteContact(int id) throws SQLException;

    void updateContact(Contact contact) throws SQLException;

    List<Contact> getContacts() throws SQLException;

    int getSizeOfBook() throws SQLException;
}
