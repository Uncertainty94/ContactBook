
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Assert;
import ru.reksoft.lab.service.ContactManager;
import ru.reksoft.lab.domain.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mishanin on 20.04.2016.
 */
public class ContactManagerTest extends Assert {
    private ContactManager cm;
    private Contact contact;
    private String name, surname, tel, mail, org , pos;

    @Before
    public void init() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("test_config.xml");
        cm = (ContactManager) context.getBean("contactManager");
        name     = "Name";
        surname  = "Surname";
        tel      = "1234";
        mail     = "mail@mail.mail";
        org      = "Organization";
        pos      = "Position";
        contact = new Contact(name, surname, tel, mail, org, pos);
    }

    @Test
    public void testAddContact() throws Exception{
        List<Contact> prevContacts = new ArrayList<>();
        prevContacts.addAll(cm.getContacts());

        cm.addContact(name, surname, tel, mail, org, pos);
        List<Contact> newContacts = cm.getContacts();
        newContacts.removeAll(prevContacts);
        for (Contact cont: newContacts){
            assertEqualsContact(contact, cont);
            cm.deleteContact(cont.getId());
        }
        assertEquals(newContacts.size(), 1);

    }

    @Test
    public void testDeleteContact() throws Exception{
        List<Contact> prevContacts = new ArrayList<>();

        cm.addContact(name, surname, tel, mail, org, pos);
        prevContacts.addAll(cm.getContacts());

        int idToDelete = findMaxId(prevContacts);
        cm.deleteContact(idToDelete);

        List<Contact> newContacts = cm.getContacts();
        prevContacts.removeAll(newContacts);
        for (Contact cont: prevContacts){
            assertEqualsContact(contact, cont);
        }
        assertEquals(prevContacts.size(), 1);
    }

    private void assertEqualsContact(Contact one, Contact two){
        assertEquals(one.getName(), two.getName());
        assertEquals(one.getSurname(), two.getSurname());
        assertEquals(one.getTelNumber(), two.getTelNumber());
        assertEquals(one.getMail(), two.getMail());
        assertEquals(one.getOrganization(), two.getOrganization());
        assertEquals(one.getPosition(), two.getPosition());
    }

    private int findMaxId(List<Contact> contacts){
        int id = 0;
        for (Contact contact: contacts)
            if (contact.getId() > id) id = contact.getId();
        return id;
    }

    @After
    public void destroy() { cm = null; }
}
