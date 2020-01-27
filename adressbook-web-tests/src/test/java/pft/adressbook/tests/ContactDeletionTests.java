package pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test

    public void testContactDeletion () {
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Олег", "Соколов", "Екатеринбург", "4951251", "sokolov85@mail.ru", "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().closeAlert();
        app.getContactHelper().returnToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }
}
