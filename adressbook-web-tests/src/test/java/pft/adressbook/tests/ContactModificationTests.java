package pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test

    public void testContactModification () {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Соколов", "Олег", "Екатеринбург", "4951251", "sokolov85@mail.ru", "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(1);
        app.getContactHelper().gotoEdit(1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Соколов", "Олег", "Екатеринбург", "4951251", "sokolov85@mail.ru", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().getUpdate();
        app.getContactHelper().returnToHomePage();
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
