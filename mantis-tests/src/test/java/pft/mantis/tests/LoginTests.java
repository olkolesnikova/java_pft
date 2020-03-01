package pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.mantis.appmanager.HttpSession;

import javax.xml.rpc.ServiceException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

    @Test

    public void testLogin() throws IOException, ServiceException {
        TestBase.skipIfNotFixed(1);
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root"));
        Assert.assertTrue(session.isLoggedInAs("administrator"));
    }
}
