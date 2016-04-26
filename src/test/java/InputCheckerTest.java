import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import ru.reksoft.lab.controller.InputChecker;
import ru.reksoft.lab.exceptions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mishanin on 20.04.2016.
 */
public class InputCheckerTest extends Assert {

    List<String> namesIn;
    List<Boolean> answersName;

    List<String> surnamesIn;
    List<Boolean> answersSurname;

    List<String> telsIn;
    List<Boolean> answersTel;

    List<String> mailsIn;
    List<Boolean> answersMail;

    List<String> orgsIn;
    List<Boolean> answersOrg;

    List<String> positionsIn;
    List<Boolean> answersPos;

/*******************************************BEFORE**********************************************************/

    @Before
    public void initName() {
        namesIn = new ArrayList<>();
        answersName = new ArrayList<>();
        namesIn.add(null);
        namesIn.add("MAx");
        namesIn.add("");
        namesIn.add("MAx qwe");
        namesIn.add("MAqqqweqAsdadax");
        answersName.add(false);
        answersName.add(true);
        answersName.add(false);
        answersName.add(false);
        answersName.add(true);
    }

    @Before
    public void initSurname() {
        surnamesIn = new ArrayList<>();
        answersSurname = new ArrayList<>();
        surnamesIn.add(null);
        surnamesIn.add("");
        surnamesIn.add("asdasd");
        surnamesIn.add("asdadadad qwe");
        surnamesIn.add("MAqqqweqAsdadax");
        answersSurname.add(false);
        answersSurname.add(false);
        answersSurname.add(true);
        answersSurname.add(false);
        answersSurname.add(true);
    }

    @Before
    public void initTel() {
        telsIn = new ArrayList<>();
        answersTel = new ArrayList<>();
        telsIn.add(null);
        telsIn.add("123123123 123 123");
        telsIn.add("123-231(2131");
        telsIn.add("12312dsad12123");
        telsIn.add("ss2123123");
        telsIn.add("123 )))))");
        telsIn.add("-----");
        telsIn.add("a-a-----a");
        answersTel.add(false);
        answersTel.add(true);
        answersTel.add(true);
        answersTel.add(false);
        answersTel.add(false);
        answersTel.add(true);
        answersTel.add(false);
        answersTel.add(false);
    }

    @Before
    public void initMail() {
        mailsIn = new ArrayList<>();
        answersMail = new ArrayList<>();
        mailsIn.add(null);              answersMail.add(false);
        mailsIn.add("qweqwe@.qwe");     answersMail.add(false);
        mailsIn.add("qwe@ada.e");       answersMail.add(true);
        mailsIn.add("123q@asd.");       answersMail.add(false);
        mailsIn.add("123qwe@asd.  ");   answersMail.add(false);
        mailsIn.add(" @  .   ");        answersMail.add(false);
        mailsIn.add("@.");              answersMail.add(false);
        mailsIn.add("111111");          answersMail.add(false);
        mailsIn.add("qweqewqwe");       answersMail.add(false);
        mailsIn.add("qweqweqwe.re");    answersMail.add(false);
        mailsIn.add("qweqwe@123.re");   answersMail.add(true);

    }

    @Before
    public void initOrg() {
        orgsIn = new ArrayList<>();
        answersOrg = new ArrayList<>();
        orgsIn.add(null);
        orgsIn.add("");
        orgsIn.add("qqq");
        orgsIn.add("weqeq qwe");
        orgsIn.add("eeeeqq");
        answersOrg.add(false);
        answersOrg.add(false);
        answersOrg.add(true);
        answersOrg.add(true);
        answersOrg.add(true);
    }

    @Before
    public void initPos() {
        positionsIn = new ArrayList<>();
        answersPos = new ArrayList<>();
        positionsIn.add(null);
        positionsIn.add("");
        positionsIn.add("qq23q");
        positionsIn.add("weqaaaq1eq qwe");
        positionsIn.add("eee441eqq");
        answersPos.add(false);
        answersPos.add(false);
        answersPos.add(true);
        answersPos.add(true);
        answersPos.add(true);
    }

/***********************************************TEST******************************************************/
    @Test
    public void checkName() {
        List<Boolean> answers = new ArrayList<>();
        for (String name : namesIn) {
            try {

                answers.add(InputChecker.checkName(name));

            } catch (NameInputException e) {
                answers.add(false);
            }
        }
        assertEquals(answersName, answers);
    }

    @Test
    public void checkSurname() {
        List<Boolean> answers = new ArrayList<>();
        for (String surname : surnamesIn) {
            try {

                answers.add(InputChecker.checkSurname(surname));

            } catch (SurnameInputException e) {
                answers.add(false);
            }
        }
        assertEquals(answersSurname, answers);
    }

    @Test
    public void checkTel() {
        List<Boolean> answers = new ArrayList<>();
        for (String tel : telsIn) {
            try {

                answers.add(InputChecker.checkTelNumber(tel));

            } catch (TelNumberInputException e) {
                answers.add(false);
            }
        }
        assertEquals(answersTel, answers);
    }

    @Test
    public void checkMail() {
        List<Boolean> answers = new ArrayList<>();
        for (String mail : mailsIn) {
            try {

                answers.add(InputChecker.checkMail(mail));

            } catch (MailInputException e) {
                answers.add(false);
            }
        }
        assertEquals(answersMail, answers);
    }

    @Test
    public void checkOrg() {
        List<Boolean> answers = new ArrayList<>();
        for (String org : orgsIn) {
            try {

                answers.add(InputChecker.checkOrganization(org));

            } catch (OrganizationInputException e) {
                answers.add(false);
            }
        }
        assertEquals(answersOrg, answers);
    }

    @Test
    public void checkPos() {
        List<Boolean> answers = new ArrayList<>();
        for (String pos : positionsIn) {
            try {

                answers.add(InputChecker.checkPosition(pos));

            } catch (PositionInputException e) {
                answers.add(false);
            }
        }
        assertEquals(answersPos, answers);
    }

/**************************************************AFTER****************************************************/

    @After
    public void destroy(){
        namesIn = null;
        surnamesIn = null;
        telsIn = null;
        mailsIn = null;
        orgsIn = null;
        positionsIn = null;

        answersName = null;
        answersSurname = null;
        answersTel = null;
        answersMail = null;
        answersOrg = null;
        answersPos = null;
    }

}

