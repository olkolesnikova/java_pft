package pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;

import java.util.Set;


public class ContactCreationTest extends TestBase {


    @Test
  public void testContactCreation() throws Exception {
        Set<ContactData> before = app.getContactHelper().all();
        app.getContactHelper().gotoNewContactPage();
        ContactData contact = new ContactData()
                .withFamily("Соколов").withName("Олег").withAddress("Екатеринбург").withTelephone("4951251").withEmail("sokolov85@mail.ru").withGroup("test1");
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
        Set<ContactData> after = app.getContactHelper().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
  }

}
