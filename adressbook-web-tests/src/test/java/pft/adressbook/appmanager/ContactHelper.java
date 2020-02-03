package pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pft.adressbook.model.ContactData;
import pft.adressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);

    }



    public void gotoNewContactPage() {
        click(By.linkText("add new"));
    }

    public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
}

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    public void fillContactForm(ContactData contactData, boolean creation) {

        field(By.name("firstname"), contactData.getName());
        field(By.name("lastname"), contactData.getFamily());
        field(By.name("address"), contactData.getAddress());
        field(By.name("home"), contactData.getTelephone());
        field(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }



    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();

    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

    }

    public void gotoEdit(int id) {
        wd.findElement(By.xpath("//img[@alt='Edit']")).click();

    }

    public void modifyContact(ContactData contact) {
        selectContactById(contact.getId());
        gotoEdit(contact.getId());
        fillContactForm(contact, false);
        getUpdate();
        contactCache = null;
        returnToHomePage();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));

    }

    public void getUpdate() {
        click(By.xpath("(//input[@name='update'])[2]"));

    }

    public void createContact(ContactData contact, boolean creation) {
        gotoNewContactPage();
        fillContactForm(contact, true);
        submitContactCreation();

        returnToHomePage();
        contactCache = null;



    }

    public void returnToHomePage() {
        if (isElementPresent(By.id ("maintable"))) {
            return;
        }
        click(By.linkText("home page"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
      return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));

            String family = cells.get(1).getText();
            String name = cells.get(2).getText();
            String address = cells.get(3).getText();
            String telephone = cells.get(4).getText();
            String email = cells.get(5).getText();
            String group = cells.get(6).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFamily(family).withName(name).withAddress(address).withTelephone(telephone).withEmail(email).withGroup(group));
        }
        return contacts;
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));

            String family = cells.get(1).getText();
            String name = cells.get(2).getText();
            String address = cells.get(3).getText();
            String telephone = cells.get(4).getText();
            String email = cells.get(5).getText();
            String group = cells.get(6).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFamily(family).withName(name).withAddress(address).withTelephone(telephone).withEmail(email).withGroup(group));
        }
        return new Contacts(contactCache);
    }

        public void deleteContact(int index) {
        selectContact(index);
        deleteSelectedContact();
        closeAlert();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
        closeAlert();
    }
}
