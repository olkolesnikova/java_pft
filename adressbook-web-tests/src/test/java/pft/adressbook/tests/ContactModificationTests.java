package pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test

    public void testContactModification () {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Олег", "Соколов", "Екатеринбург", "4951251", "sokolov85@mail.ru", "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().gotoEdit();
        app.getContactHelper().fillContactForm(new ContactData("Олег", "Соколов", "Екатеринбург", "4951251", "sokolov85@mail.ru", null), false);
        app.getContactHelper().getUpdate();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }

}
