package ru.reksoft.lab.model;

/**
 * Created by mishanin on 19.04.2016.
 */
public class Contact {
    private String name, surname, telNumber, mail, organization, position;

    public Contact(String name, String surname, String telNumber, String mail, String organization, String position) {
        this.name = name;
        this.surname = surname;
        this.telNumber = telNumber;
        this.mail = mail;
        this.organization = organization;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getMail() {
        return mail;
    }

    public String getOrganization() {
        return organization;
    }

    public String getPosition() {
        return position;
    }
}
