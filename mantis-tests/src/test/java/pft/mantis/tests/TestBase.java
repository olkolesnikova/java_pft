package pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnect;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pft.mantis.appmanager.ApplicationManager;
import pft.mantis.model.Issue;
import pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        //app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        //app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

    public static boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {

        boolean isFixed = false;
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withId(issueId);
        MantisConnectPortType mc = app.soap().getMantisConnect();
        ObjectRef status =  mc.mc_issue_get("administrator","root", BigInteger.valueOf(issueId)).getStatus();
        if (status.getName().equals("fixed")) {
            isFixed = true;
        }
        return isFixed;
    }

    public static void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
