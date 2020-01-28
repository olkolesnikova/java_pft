package pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test

    public void testContactModification () {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Олег", "Соколов", "Екатеринбург", "4951251", "sokolov85@mail.ru", "test1"), true);
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before - 1);
        app.getContactHelper().gotoEdit();
        app.getContactHelper().fillContactForm(new ContactData("Олег", "Соколов", "Екатеринбург", "4951251", "sokolov85@mail.ru", null), false);
        app.getContactHelper().getUpdate();
        app.getContactHelper().returnToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }

}
