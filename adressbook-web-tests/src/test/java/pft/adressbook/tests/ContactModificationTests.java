package pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;
import pft.adressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod

    public void ensurePreconditions() {
        if (app.getContactHelper().all().size() == 0) {
            app.getContactHelper().createContact(new ContactData()
                    .withFamily("Соколов").withName("Олег").withAddress("Екатеринбург").withTelephone("4951251")
                    .withMobile("222").withWork("333").withEmail("sokolov85@mail.ru").withGroup("test1"), true);
        }
    }

    @Test

    public void testContactModification () {
        Contacts before = app.getContactHelper().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFamily("Соколов").withName("Олег").withAddress("Екатеринбург").withTelephone("4951251")
                .withMobile("222").withWork("333").withEmail("sokolov85@mail.ru").withGroup("test1");
        app.getContactHelper().modifyContact(contact);
        assertEquals(app.getContactHelper().getContactCount(), before.size());
        Contacts after = app.getContactHelper().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
