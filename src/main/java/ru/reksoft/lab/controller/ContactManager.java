package ru.reksoft.lab.controller;

import ru.reksoft.lab.model.Contact;
import ru.reksoft.lab.model.Provider;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by mishanin on 19.04.2016.
 */
public class ContactManager {

    private int maxContacts;
    private Provider provider;

    public ContactManager(Provider provider){
        this.provider = provider;
    }

    public void setMaxContacts(int maxContacts) {
        this.maxContacts = maxContacts;
    }

    public List<Contact> getContacts() throws SQLException {
        return provider.getContacts();
    }

    public void addContact(String name, String surname, String telNumber, String mail, String organization, String position) throws Exception{

        provider.addContact(name, surname, telNumber, mail, organization, position);

    }

    public void deleteContact(int id) throws Exception{
        provider.deleteContact(id);
    }

    public int getCurrentSizeOfBook() {
        try {
            return provider.getSizeOfBook();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getFreeSpaceOfBook() {
        return maxContacts - getCurrentSizeOfBook();
    }
}
