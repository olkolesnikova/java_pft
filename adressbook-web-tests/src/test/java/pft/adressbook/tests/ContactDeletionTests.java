package pft.adressbook.tests;

import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test

    public void testContactDeletion () {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Олег", "Соколов", "Екатеринбург", "4951251", "sokolov85@mail.ru", "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().closeAlert();

    }
}
