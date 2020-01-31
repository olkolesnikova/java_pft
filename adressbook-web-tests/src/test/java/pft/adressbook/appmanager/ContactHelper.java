package pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pft.adressbook.model.ContactData;

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


    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));

    }

    public void gotoEdit(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();

    }

    public void getUpdate() {
        click(By.xpath("(//input[@name='update'])[2]"));

    }

    public void createContact(ContactData contact, boolean creation) {
        gotoNewContactPage();
        fillContactForm(contact, true);
        submitContactCreation();
        returnToHomePage();


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

            ContactData contact = new ContactData(family, name, address, telephone, email, group);
            contacts.add(contact);
        }
        return contacts;
    }
}
