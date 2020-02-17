package pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pft.adressbook.model.ContactData;
import pft.adressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

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
        field(By.name("mobile"), contactData.getMobile());
        field(By.name("work"), contactData.getWork());
        field(By.name("email"), contactData.getEmail());
        ;

        //attach(By.name("photo"), contactData.getPhoto());

    }



    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();

    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

    }

    public void gotoEdit(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();

    }

    public void modifyContact(ContactData contact) {
        selectContactById(contact.getId());
        gotoEdit(contact.getId());
        fillContactForm(contact, true);
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

    public void createContact(ContactData contact) {
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
            String telephone = cells.get(5).getText();
            String email = cells.get(4).getText();
            String group = cells.get(6).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFamily(family).withName(name).withAddress(address).withEmail(email)
                    .withTelephone(telephone));
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

            String allPhones = cells.get(5).getText();
            String allEmail = cells.get(4).getText();

            String group = cells.get(6).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFamily(family).withName(name).withAddress(address)
                    .withAllPhones(allPhones).withAllEmail(allEmail));
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

    public ContactData infoFromEditForm(ContactData contact) {
        selectContactById(contact.getId());
        gotoEdit(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String telephone = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFamily(lastname).withName(firstname).withAddress(address).withTelephone(telephone).withMobile(mobile).withWork(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3);

    }
}
