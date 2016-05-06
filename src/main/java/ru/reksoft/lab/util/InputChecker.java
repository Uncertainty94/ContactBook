package ru.reksoft.lab.util;

import ru.reksoft.lab.exceptions.*;

/**
 * Created by mishanin on 20.04.2016.
 */
public class InputChecker {

    public static boolean checkName(String name) throws NameInputException {
        boolean result = (name != null) && (!"".equals(name)) && (name.split(" ").length < 2);
        if (!result){
            throw new NameInputException("Name shouldn't be empty and consists of 1 word. Try again, please.");
        }
        return true;
    }

    public static boolean checkSurname(String surname) throws SurnameInputException {
        boolean result = (surname != null) && (!"".equals(surname)) && (surname.split(" ").length < 2);
        if (!result){
            throw new SurnameInputException("Surname shouldn't be empty and consists of 1 word. Try again, please.");
        }
        return true;
    }

    public static boolean checkTelNumber(String telNumber) throws TelNumberInputException {
        boolean result = (telNumber != null) && (!"".equals(telNumber)) &&
                (telNumber.replace("-", "").replace("(", "").replace(")", "").replace(" ", "").length() > 0) &&
                (telNumber.replace("-", "").replace("(", "").replace(")", "").replace(" ", "").replaceAll("\\d+", "").length() == 0) ;

        if (!result) {
            throw new TelNumberInputException("Phone number should contain the numbers 0-9, chars '-', '(', ')' and spaces. Try again, please.");
        }
        return true;
    }

    public static boolean checkMail(String mail) throws MailInputException {
        boolean result = (mail != null) && (!"".equals(mail)) && (mail.matches("^\\w+@\\w+\\.\\w+"));

        if (!result){
            throw new MailInputException("Mail should matches <name>@<mail>.<domen>. Try again, please.");
        }
        return true;
    }

    public static boolean checkOrganization(String organization) throws OrganizationInputException {
        boolean result = (organization != null) && (!"".equals(organization));
        if (!result){
            throw new OrganizationInputException("Organization shouldn't be empty. Try again, please.");
        }
        return true;
    }

    public static boolean checkPosition(String position) throws PositionInputException {
        boolean result = (position != null) && (!"".equals(position));
        if (!result){
            throw new PositionInputException("Position shouldn't be empty. Try again, please.");
        }
        return true;
    }
}
