package pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pft.adressbook.ContactData;

public class ContactHelper {

    private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        this.wd = wd;

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

    private void click(By locator) {
        wd.findElement(locator).click();
    }

    public void fillContactForm(ContactData contactData) {

        field(By.name("firstname"), contactData.getName());
        field(By.name("lastname"), contactData.getFamily());
        field(By.name("address"), contactData.getAddress());
        field(By.name("home"), contactData.getTelephone());
        field(By.name("email"), contactData.getEmail());
    }

    private void field(By locator, String text) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    public void selectContact() {

        click(By.id("20"));
    }



}
