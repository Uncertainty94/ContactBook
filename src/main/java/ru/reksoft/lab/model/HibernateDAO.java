package ru.reksoft.lab.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mishanin on 27.04.2016.
 */
public class HibernateDAO extends Provider {

    SessionFactory sessionFactory;
    Session session;

    public HibernateDAO(){
        super();
        sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        session = sessionFactory.openSession();
    }

    @Override
    public void addContact(String name, String surname, String telNumber, String mail, String organization, String position)
            throws SQLException {
        session.beginTransaction();
        Contact contact = new Contact(name, surname, telNumber, mail, organization, position);
        session.save(contact);
        session.getTransaction().commit();
    }

    @Override
    public void deleteContact(int id) throws SQLException {
        session.beginTransaction();
        Contact contact = (Contact) session.get(Contact.class, id);
        session.delete(contact);
        session.getTransaction().commit();
    }

    @Override
    public void updateContact(Contact contact) throws SQLException {

    }

    @Override
    public List<Contact> getContacts() throws SQLException {
        session.beginTransaction();
        List<Contact> contacts = new ArrayList<>();
        List contactsFromBd = session.createQuery("FROM ru.reksoft.lab.model.Contact").list();
        for (Iterator iterator =
             contactsFromBd.iterator(); iterator.hasNext();){
            contacts.add((Contact) iterator.next());
        }
        session.getTransaction().commit();
        return contacts;
    }

    @Override
    public int getSizeOfBook() throws SQLException{
        return session.createQuery("FROM ru.reksoft.lab.model.Contact").list().size();
    }
}
