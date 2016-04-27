package ru.reksoft.lab.model;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by mishanin on 27.04.2016.
 */
public interface Provider {
    int ID              = 1;
    int NAME            = 2;
    int SURNAME         = 3;
    int TEL_NUM         = 4;
    int MAIL            = 5;
    int ORGANIZATION    = 6;
    int POSITION        = 7;

    String JDBC_DRIVER = "org.postgresql.Driver";
    String DB_URL = "jdbc:postgresql://localhost:5432/contact_book";
    String TABLE_NAME = "contacts_table";

    String USER = "postgres";
    String PASS = "admin";

    void addContact(String name, String surname, String telNumber, String mail, String organization, String position)
            throws SQLException;

    void deleteContact(int id) throws SQLException;

    void updateContact(Contact contact) throws SQLException;

    List<Contact> getContacts() throws SQLException;

    int getSizeOfBook() throws SQLException;
}
