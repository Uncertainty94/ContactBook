package ru.reksoft.lab.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.reksoft.lab.model.Contact;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mishanin on 19.04.2016.
 */
public class ParserXML {

    private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    private DocumentBuilder db = null;
    private Document doc = null;

    List<Contact> readXml(String filePath) throws Exception{
        List<Contact> contacts = new ArrayList<>();
        db = dbf.newDocumentBuilder();
        try {
            doc = db.parse(new File(filePath));
            NodeList nodeLst = doc.getElementsByTagName("Contact");
            String name, surname, telNumber, mail, organization, position;

            for (int i = 0; i < nodeLst.getLength(); i++) {
                Element item = (Element) nodeLst.item(i);
                name = item.getAttribute("name");
                surname = item.getAttribute("surname");
                telNumber = item.getAttribute("telNumber");
                mail = item.getAttribute("mail");
                organization = item.getAttribute("organization");
                position = item.getAttribute("position");
                contacts.add(new Contact(name, surname, telNumber, mail, organization, position));
            }
        } catch (FileNotFoundException e) {
            saveInXml(new ArrayList<>(), filePath);
        }
        return contacts;
    }

    void saveInXml (List<Contact> contacts, String filePath) throws Exception{
        doc = db.newDocument();
        Element rootElement = doc.createElement("Contacts");
        for (Contact contact: contacts) {
            Element contactElement = doc.createElement("Contact");
            contactElement.setAttribute("name", contact.getName());
            contactElement.setAttribute("surname", contact.getSurname());
            contactElement.setAttribute("telNumber", contact.getTelNumber());
            contactElement.setAttribute("mail", contact.getMail());
            contactElement.setAttribute("organization", contact.getOrganization());
            contactElement.setAttribute("position", contact.getPosition());
            rootElement.appendChild(contactElement);
        }
        doc.appendChild(rootElement);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(new File(filePath)));
    }
}
