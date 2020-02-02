package pft.adressbook.tests;

import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;
import pft.adressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTest extends TestBase {


    @Test
  public void testContactCreation() throws Exception {
        Contacts before = app.getContactHelper().all();
        app.getContactHelper().gotoNewContactPage();
        ContactData contact = new ContactData()
                .withFamily("Соколов").withName("Олег").withAddress("Екатеринбург").withTelephone("4951251").withEmail("sokolov85@mail.ru").withGroup("test1");
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
        Contacts after = app.getContactHelper().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
