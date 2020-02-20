package pft.adressbook.tests;

import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;
import pft.adressbook.model.Contacts;
import pft.adressbook.model.GroupData;
import pft.adressbook.model.Groups;

public class DeleteContactFromGroup extends TestBase{

    @Test

    public void testDeleteContactFromGroup() {
        Contacts contactBefore = app.db().contacts();
        Groups groups = app.db().groups();
        GroupData group = app.db().getGroupsFromDb(NewContact(contactBefore, groups).getId());
        Contacts contacts = group.getContacts();
        ContactData deletedContact = contacts.iterator().next();
        app.getContactHelper().deleteGroupFromContact(deletedContact, group.getId());

    }

    private GroupData NewContact(Contacts beforeContact, Groups groups) {
        for (ContactData contact : beforeContact) {
            if (contact.getGroups().size() > 0) {
                Groups groupsWithContacts = contact.getGroups();
                return groups.iterator().next();
            }
        }
        ContactData addedContact = beforeContact.iterator().next();
        GroupData group = groups.iterator().next();
        app.getContactHelper().addContactToGroup(addedContact, group);
        app.getContactHelper().getCurrentGroupPage(group);
        return group;
    }
}
