package pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;

import java.util.List;


public class ContactCreationTest extends TestBase {


    @Test
  public void testContactCreation() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().gotoNewContactPage();
        app.getContactHelper().fillContactForm(new ContactData ("Олег", "Соколов", "Екатеринбург", "4951251", "sokolov85@mail.ru", "test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
      

  }

}
