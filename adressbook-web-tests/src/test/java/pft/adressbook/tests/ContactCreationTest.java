package pft.adressbook.tests;

import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;


public class ContactCreationTest extends TestBase {


    @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().gotoNewContactPage();
    app.getContactHelper().fillContactForm(new ContactData ("Олег", "Соколов", "Москва", "4951251", "sokolov85@mail.ru"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();

  }

}
