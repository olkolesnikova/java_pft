package pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.mantis.model.MailMessage;
import pft.mantis.model.UserData;
import pft.mantis.model.Users;
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

        Users users = app.db().users();
        UserData selectedUser = users.iterator().next();
        int userId = selectedUser.getId();
        String user = selectedUser.getName();
        String email = selectedUser.getEmail();
        String password = selectedUser.getPassword();
        String admin = "administrator";
        String adminpass = "root";

        app.login_admin().loginAdmin(admin, adminpass, userId);
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


