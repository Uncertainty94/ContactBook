package ru.reksoft.lab.controller;

import ru.reksoft.lab.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mishanin on 19.04.2016.
 */
public class ContactManager {

    private int maxContacts;
    private List<Contact> contacts;
    private ParserXML parser;
    private String filePath;

    public ContactManager(ParserXML parser, String filePath){
        this.parser = parser;
        this.filePath = filePath;
        try {
            readContacts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMaxContacts(int maxContacts) {
        this.maxContacts = maxContacts;
    }

    private void readContacts() throws Exception{
        contacts = parser.readXml(filePath);
    }

    public void readContacts(String newFile) throws Exception{
        this.filePath = newFile;
        readContacts();
    }

    private void saveContacts() throws Exception{
        parser.saveInXml(contacts, filePath);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void addContact(String name, String surname, String telNumber, String mail, String organization, String position) throws Exception{

        contacts.add(new Contact(name, surname, telNumber, mail, organization, position));
        saveContacts();
    }

    public List<Contact> deleteContact(String name, String surname) throws Exception{
        List<Contact> contactsToDelete = new ArrayList<>();
        for (Contact contact : contacts) {
            if (name.toLowerCase().equals(contact.getName().toLowerCase()) && surname.toLowerCase().equals(contact.getSurname().toLowerCase())) {
                contactsToDelete.add(contact);
            }
        }
        if (contactsToDelete.size() == 1) {
            deleteContact(contactsToDelete.get(0));
        }
        return contactsToDelete;
    }

    public void deleteContact(Contact contact) throws Exception{
        contacts.remove(contact);
        saveContacts();
    }

    public int getCurrentSizeOfBook() {
        return contacts.size();
    }

    public int getFreeSpaceOfBook() {
        return maxContacts - contacts.size();
    }
}
