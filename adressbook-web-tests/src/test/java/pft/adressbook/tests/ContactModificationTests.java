package pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;

import java.net.BindException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod

    public void ensurePreconditions() {
        if (app.getContactHelper().getContactList().size() == 0) {
            app.getContactHelper().createContact(new ContactData()
                    .withFamily("Соколов").withName("Олег").withAddress("Екатеринбург").withTelephone("4951251").withEmail("sokolov85@mail.ru").withGroup("test1"), true);
        }
    }

    @Test

    public void testContactModification () {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        ContactData contact = new ContactData()
                .withId(before.get(index).getId()).withFamily("Соколов").withName("Олег").withAddress("Екатеринбург").withTelephone("4951251").withEmail("sokolov85@mail.ru").withGroup("test1");
        app.getContactHelper().modifyContact(contact, index);
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
