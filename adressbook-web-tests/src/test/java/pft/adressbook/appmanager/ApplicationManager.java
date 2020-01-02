package pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pft.adressbook.ContactData;
import pft.adressbook.model.GroupData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private WebDriver wd;

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/");
        login("user", "pass", By.xpath("//input[@value='Login']"), "admin", "secret");
    }

    private void login(String user, String pass, By xpath, String username, String password) {
      wd.findElement(By.name(user)).clear();
      wd.findElement(By.name(user)).sendKeys(username);
      wd.findElement(By.name(pass)).click();
      wd.findElement(By.name(pass)).clear();
      wd.findElement(By.name(pass)).sendKeys(password);
      wd.findElement(xpath).click();
    }

    public void returnToGroupPage(String s) {
      wd.findElement(By.linkText(s)).click();
    }

    public void submitGroupCreation(String submit) {
      wd.findElement(By.name(submit)).click();
    }

    public void fillGroupForm(GroupData groupData) {
      wd.findElement(By.name("group_name")).click();
      wd.findElement(By.name("group_name")).clear();
      wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
      wd.findElement(By.name("group_header")).clear();
      wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
      wd.findElement(By.name("group_footer")).click();
      wd.findElement(By.name("group_footer")).clear();
      wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    public void initGroupCreation(String s) {
      wd.findElement(By.name(s)).click();
    }

    public void gotoGroupPage(String groups) {
      wd.findElement(By.linkText(groups)).click();
    }

    public void stop() {
        wd.quit();
    }

    public boolean isElementPresent(By by) {
      try {
        wd.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    public boolean isAlertPresent() {
      try {
        wd.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }

    public void deleteSelectedGroups(String delete) {
      wd.findElement(By.name(delete)).click();
    }

    public void selectGroup(String s) {
      wd.findElement(By.name(s)).click();
    }

    public void returnToHomePage(String s) {
        wd.findElement(By.linkText(s)).click();
    }


        public void gotoNewContactPage(String s) {
        wd.findElement(By.linkText(s)).click();
    }

    public void submitContactCreation() {
        wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void fillContactForm(ContactData contactData) {

        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(contactData.getName());
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(contactData.getFamily());
        wd.findElement(By.name("address")).click();
        wd.findElement(By.name("address")).clear();
        wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
        wd.findElement(By.name("home")).click();
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(contactData.getTelephone());
        wd.findElement(By.name("email")).click();
        wd.findElement(By.name("email")).clear();
        wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }
}
