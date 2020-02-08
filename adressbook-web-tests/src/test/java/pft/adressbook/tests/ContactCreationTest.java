package pft.adressbook.tests;

import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;
import pft.adressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTest extends TestBase {


    @Test
  public void testContactCreation() throws Exception {
        Contacts before = app.getContactHelper().all();
        app.getContactHelper().gotoNewContactPage();
        File photo = new File("src/test/resources/volk.jpg");
        ContactData contact = new ContactData()
                .withFamily("Соколов").withName("Олег").withAddress("Екатеринбург").withTelephone("4951251")
                .withMobile("222").withWork("333").withEmail("sokolov85@mail.ru").withPhoto(photo).withGroup("test1");
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size() + 1));
        Contacts after = app.getContactHelper().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }


}
