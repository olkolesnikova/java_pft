package pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pft.adressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);

    }

    public void returnToHomePage(String s) {
        click(By.linkText(s));
    }

    public void gotoNewContactPage(String s) {
        click(By.linkText(s));
    }

    public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
}

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    public void fillContactForm(ContactData contactData) {

        field(By.name("firstname"), contactData.getName());
        field(By.name("lastname"), contactData.getFamily());
        field(By.name("address"), contactData.getAddress());
        field(By.name("home"), contactData.getTelephone());
        field(By.name("email"), contactData.getEmail());
    }

    public void selectContact() {

        click(By.id("13"));
    }


    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();

    }

    public void gotoEdit() {
        click(By.xpath("//img[@alt='Edit']"));

    }

    public void getUpdate() {
        click(By.xpath("(//input[@name='update'])[2]"));

    }
}
