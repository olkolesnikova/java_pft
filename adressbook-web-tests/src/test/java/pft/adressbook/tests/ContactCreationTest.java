package pft.adressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;
import pft.adressbook.model.Contacts;
import pft.adressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTest extends TestBase {


    @DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{new ContactData().withName("Петр").withFamily("Петров").withAddress("Москва").withGroup("test1")});
        list.add(new Object[]{new ContactData().withName("Иван").withFamily("Иванов").withAddress("Екатеринбург").withGroup("test1")});
        list.add(new Object[]{new ContactData().withName("Семен").withFamily("Семенов").withAddress("Новосибирск").withGroup("test1")});
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {
        Contacts before = app.getContactHelper().all();
        app.getContactHelper().gotoNewContactPage();
        File photo = new File("src/test/resources/volk.jpg");

        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size() + 1));
        Contacts after = app.getContactHelper().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }


}
