package pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod

    public void ensurePreconditions() {
        if (app.getContactHelper().getContactList().size() == 0) {
            app.getContactHelper().createContact(new ContactData()
                    .withFamily("Соколов").withName("Олег").withAddress("Екатеринбург").withTelephone("4951251").withEmail("sokolov85@mail.ru").withGroup("test1"), true);
        }
    }

    @Test

    public void testContactDeletion () {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        app.getContactHelper().deleteContact(index);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
    }

}
