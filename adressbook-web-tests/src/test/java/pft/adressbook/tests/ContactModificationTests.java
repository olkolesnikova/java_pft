package pft.adressbook.tests;

import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test

    public void testContactModification () {

        app.getContactHelper().selectContact();
        app.getContactHelper().gotoEdit();
        app.getContactHelper().fillContactForm(new ContactData("Олег", "Соколов", "Екатеринбург", "4951251", "sokolov85@mail.ru"));
        app.getContactHelper().getUpdate();
        app.getContactHelper().returnToHomePage();
    }

}
