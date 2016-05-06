package ru.reksoft.lab.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.lab.dao.ContactDao;
import ru.reksoft.lab.domain.Contact;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mishanin on 27.04.2016.
 */
@Transactional
public class HiberDaoImpl implements ContactDao {

//    @Autowired
//    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;
    private Session session;

    public HiberDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
        session = sessionFactory.openSession();
    }

//    public HiberDaoImpl(){
//        session = this.sessionFactory.openSession();
//    }

    @Override
    public void addContact(String name, String surname, String telNumber, String mail, String organization, String position)
            throws SQLException {
        Contact contact = new Contact(name, surname, telNumber, mail, organization, position);
        session.save(contact);
    }

    @Override
    public void deleteContact(int id) throws SQLException {
        Contact contact = (Contact) session.get(Contact.class, id);
        session.delete(contact);
    }

    @Override
    public void updateContact(Contact contact) throws SQLException {

    }

    @Override
    public List<Contact> getContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        List contactsFromBd = session.createQuery("FROM ru.reksoft.lab.domain.Contact").list();
        for (Iterator iterator =
             contactsFromBd.iterator(); iterator.hasNext();){
            contacts.add((Contact) iterator.next());
        }
        return contacts;
    }

    @Override
    public int getSizeOfBook() throws SQLException{
        return session.createQuery("FROM ru.reksoft.lab.domain.Contact").list().size();
    }
}
