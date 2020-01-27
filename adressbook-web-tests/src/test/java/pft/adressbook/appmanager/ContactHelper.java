package pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pft.adressbook.model.ContactData;

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



    public void selectContact() {

        click(By.name("selected[]"));
    }


    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));

    }

    public void gotoEdit() {
        click(By.xpath("//img[@alt='Edit']"));

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
}
