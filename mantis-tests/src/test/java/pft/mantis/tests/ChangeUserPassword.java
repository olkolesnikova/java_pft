package pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangeUserPassword extends TestBase{

    @BeforeMethod

    public void startMailServer() {
        app.mail().start();
    }

    @Test

    public void testChangeUserPassword() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String user = String.format("user1582570475822", now);
        String password = "password";
        String email = String.format("user1582570475822@localhost.localdomain", now);
        String admin = "administrator";
        String adminpass = "root";

        app.login_admin().loginAdmin(admin, adminpass);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)

    public void stopMailServer() {
        app.mail().stop();
    }
}


