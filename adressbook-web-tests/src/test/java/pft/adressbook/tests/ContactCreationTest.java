package pft.adressbook.tests;

import org.testng.annotations.Test;
import pft.adressbook.ContactData;


public class ContactCreationTest extends TestBase {


    @Test
  public void testContactCreation() throws Exception {
    app.gotoNewContactPage("add new");
    app.fillContactForm(new ContactData ("Олег", "Соколов", "Москва", "4951251", "sokolov85@mail.ru"));
    app.submitContactCreation();
    app.returnToHomePage("home page");

  }


}
