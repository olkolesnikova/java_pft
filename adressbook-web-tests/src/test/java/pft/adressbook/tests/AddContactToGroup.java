package pft.adressbook.tests;

import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;
import pft.adressbook.model.Contacts;
import pft.adressbook.model.GroupData;
import pft.adressbook.model.Groups;

public class AddContactToGroup extends TestBase {


    @Test

    public void testAddContactToGroup() {
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        ContactData addedContact = NewContact(before);
        app.getContactHelper().addContactToGroup(addedContact, group);
    }

    private ContactData NewContact(Contacts before) {
        for (ContactData contact : before) {
            if (contact.getGroups().size() == 0) {
                return contact;
            }
        }

        app.getContactHelper().createContact(new ContactData().withName("111").withFamily("222").withAddress("333"));
        Contacts newList = app.db().contacts();
        return newList.iterator().next();
    }
}
