package pft.adressbook.tests;

import org.testng.annotations.Test;
import pft.adressbook.ContactData;


public class ContactCreationTest extends TestBase {


    @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().gotoNewContactPage("add new");
    app.getContactHelper().fillContactForm(new ContactData ("Олег", "Соколов", "Москва", "4951251", "sokolov85@mail.ru"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage("home page");

  }

}
