package ru.reksoft.lab;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.reksoft.lab.controller.ContactManager;
import ru.reksoft.lab.controller.InputChecker;
import ru.reksoft.lab.exceptions.InputSpellException;
import ru.reksoft.lab.model.Contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by mishanin on 19.04.2016.
 */
public class Main {
    private static BufferedReader br;
    private static ContactManager cm;

    public static void main(String[] args) throws IOException{

        br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));

        ApplicationContext context =
                new ClassPathXmlApplicationContext("config.xml");

        cm = (ContactManager) context.getBean("contactManager");

        String currentCommand;
        boolean exitFlag = false;
        System.out.println("Contact Book 1.0");

        if (!askForNewFile()) return;

        while (!exitFlag) {
            boolean trueCommand = false;
            while (!trueCommand) {
                printCommands();
                currentCommand = br.readLine().toLowerCase().trim();
                String[] commandAsArray = currentCommand.split(" ");
                if (commandAsArray.length == 1) {
                    switch (currentCommand) {
                        case "add":
                            trueCommand = true;
                            addCommand();
                            break;
                        case "show":
                            trueCommand = true;
                            showCommand();
                            break;
                        case "exit":
                            trueCommand = true;
                            exitFlag = true;
                            System.out.println("Good buy!");
                            break;
                        default:
                            System.out.printf("Sorry. Use commands correctly.");
                            break;
                    }
                } else if (commandAsArray.length == 3 && "delete".equals(commandAsArray[0])) {
                    deleteCommand(commandAsArray);
                } else {
                    System.out.println("Sorry. Use command correctly.");
                }
            }

        }
    }

    private static void printCommands() {
        System.out.printf("Command list:\nshow - for show all book\nadd - to add a new contact\ndelete name surname - to delete contact\nexit - to close the application\n>>> ");
    }

    private static boolean checkAnswerToDelete(String answer, int maxVal) {
        int val = 0;
        try {
            val = Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            return false;
        }
        if (val >= 1 && val <= maxVal)
            return true;
        return false;
    }

    private static void showContactInfo(Contact contact) {
        System.out.printf("Name: " + contact.getName() + ";\t");
        System.out.printf("Surname: " + contact.getSurname() + ";\t");
        System.out.printf("Telephone number: " + contact.getTelNumber() + ";\t");
        System.out.printf("Mail: " + contact.getMail() + ";\t");
        System.out.printf("Organization: " + contact.getOrganization() + ";\t");
        System.out.printf("Position: " + contact.getPosition() + ";\t\n");
    }

    private static boolean askForNewFile(){
        String file;
        boolean newFileFlag = false;
        System.out.printf("Do you want to open your own file? Y/N: ");
        try {
            while (!newFileFlag) {
                String answer = br.readLine();
                switch (answer.toLowerCase()) {
                    case "yes":
                    case "y":
                        newFileFlag = true;
                        System.out.print("Enter a filename: ");
                        file = br.readLine();
                        cm.readContacts(file);
                        break;
                    case "no":
                    case "n":
                        newFileFlag = true;
                        break;
                    default:
                        System.out.printf("Please enter Y/N: ");
                        break;
                }
            }
            System.out.println("Data loaded successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private static void addCommand(){
        if (cm.getFreeSpaceOfBook() != 0) {
            String name, surname, telNumber, mail, organization, position;
            try {
                boolean goodSpell = false;
                do {
                    System.out.printf("\tName: ");
                    name = br.readLine().trim();
                    try {
                        goodSpell = InputChecker.checkName(name);
                    } catch (InputSpellException e) {
                        System.out.println("\t\t" + e.getMessage());
                    }

                } while (!goodSpell);
                goodSpell = false;
                do {
                    System.out.printf("\tSurname: ");
                    surname = br.readLine().trim();
                    try {
                        goodSpell = InputChecker.checkSurname(surname);
                    } catch (InputSpellException e) {
                        System.out.println("\t\t" + e.getMessage());
                    }

                } while (!goodSpell);
                goodSpell = false;
                do {
                    System.out.printf("\tTelephone number: ");
                    telNumber = br.readLine().trim();
                    try {
                        goodSpell = InputChecker.checkTelNumber(telNumber);
                    } catch (InputSpellException e) {
                        System.out.println("\t\t" + e.getMessage());
                    }

                } while (!goodSpell);
                goodSpell = false;
                do {
                    System.out.printf("\tMail: ");
                    mail = br.readLine().trim();
                    try {
                        goodSpell = InputChecker.checkMail(mail);
                    } catch (InputSpellException e) {
                        System.out.println("\t\t" + e.getMessage());
                    }

                } while (!goodSpell);
                goodSpell = false;
                do {
                    System.out.printf("\tOrganization: ");
                    organization = br.readLine().trim();
                    try {
                        goodSpell = InputChecker.checkOrganization(organization);
                    } catch (InputSpellException e) {
                        System.out.println("\t\t" + e.getMessage());
                    }

                } while (!goodSpell);
                goodSpell = false;
                do {
                    System.out.printf("\tPosition: ");
                    position = br.readLine().trim();
                    try {
                        goodSpell = InputChecker.checkPosition(position);
                    } catch (InputSpellException e) {
                        System.out.println("\t\t" + e.getMessage());
                    }

                } while (!goodSpell);

                try {
                    cm.addContact(name, surname, telNumber, mail, organization, position);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return;
                }
                System.out.println("Now " + cm.getCurrentSizeOfBook() + " contacts in the book. You can add " + cm.getFreeSpaceOfBook() + " more.");

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else
            System.out.println("Book is full at " + cm.getCurrentSizeOfBook() + " contacts. Delete someone.");
    }

    private static void showCommand(){
        List<Contact> contacts = cm.getContacts();
        if (!contacts.isEmpty()) {
            int index = 1;
            for (Contact contact : contacts) {
                System.out.printf(index + ". ");
                showContactInfo(contact);
                index++;
            }
        } else {
            System.out.println("Book is empty. You can add " + cm.getFreeSpaceOfBook() + " contacts.");
        }
    }

    private static void deleteCommand(String[] commandAsArray) throws IOException{
        try {
            List<Contact> listToDelete = cm.deleteContact(commandAsArray[1], commandAsArray[2]);
            if (!listToDelete.isEmpty()) {
                if (listToDelete.size() == 1) {
                    System.out.println("Contact deleted");
                    if (cm.getFreeSpaceOfBook() != 0)
                        System.out.println("Now " + cm.getCurrentSizeOfBook() + " contacts in the book. You can add " + cm.getFreeSpaceOfBook() + " more.");
                    else
                        System.out.println("Book is full at " + cm.getCurrentSizeOfBook() + " contacts. Delete someone.");
                } else {
                    System.out.println("Found " + listToDelete.size() + " matches. Please, Choose one.");
                    int index = 1;
                    for (Contact contact : listToDelete) {
                        System.out.printf(index + ". ");
                        showContactInfo(contact);
                        index++;
                    }
                    String answer;
                    do {
                        answer = br.readLine();
                    } while (!checkAnswerToDelete(answer, listToDelete.size()));

                    int indexForDelete = Integer.parseInt(answer);
                    cm.deleteContact(listToDelete.get(indexForDelete - 1));
                    System.out.println("Contact deleted");
                    System.out.println("Now " + cm.getCurrentSizeOfBook() + " contacts in the book. You can add " + cm.getFreeSpaceOfBook() + " more.");
                }
            } else {
                System.out.println("No matches found. Please try again.");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}