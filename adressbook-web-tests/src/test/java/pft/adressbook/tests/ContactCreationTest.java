package pft.adressbook.tests;

import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;


public class ContactCreationTest extends TestBase {


    @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().gotoNewContactPage();
    app.getContactHelper().fillContactForm(new ContactData ("Олег", "Соколов", "Екатеринбург", "4951251", "sokolov85@mail.ru", "test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();

  }

}
