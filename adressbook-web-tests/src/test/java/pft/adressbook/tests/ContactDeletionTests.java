package pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;
import pft.adressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod

    public void ensurePreconditions() {
        if (app.getContactHelper().all().size() == 0) {
            app.getContactHelper().createContact(new ContactData()
                    .withFamily("Соколов").withName("Олег").withAddress("Екатеринбург").withTelephone("4951251")
                    .withMobile("222").withWork("333").withEmail("sokolov85@mail.ru"));
        }
    }

    @Test

    public void testContactDeletion () {
        Contacts before = app.getContactHelper().all();
        ContactData deletedContact = before.iterator().next();
        app.getContactHelper().delete(deletedContact);
        assertEquals(app.getContactHelper().getContactCount(), before.size() - 1);
        Contacts after = app.getContactHelper().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
