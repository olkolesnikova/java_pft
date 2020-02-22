package pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;
import pft.adressbook.model.Contacts;
import pft.adressbook.model.GroupData;
import pft.adressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroup extends TestBase{

    @BeforeMethod

    public void ensurePreconditions() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        if (contacts.size() == 0) {
            app.getContactHelper().createContact(new ContactData()
                    .withFamily("Соколов").withName("Олег").withAddress("Екатеринбург").withTelephone("4951251")
                    .withMobile("222").withWork("333").withEmail("sokolov85@mail.ru").withInGroup(groups.iterator().next()));
        }

        if (groups.size() == 0) {
            app.goTo().groupPage();
            app.group().create (new GroupData().withName("test 1"));
        }
    }

    @Test

    public void testDeleteContactFromGroup() {
        Contacts contactBefore = app.db().contacts();
        Groups groups = app.db().groups();
        GroupData group = app.db().getGroupsFromDb(groupWithContacts(contactBefore, groups).getId());
        Contacts contacts = group.getContacts();
        ContactData deletedContact = contacts.iterator().next();
        app.getContactHelper().deleteGroupFromContact(deletedContact, group.getId());
        Contacts afterDeletionContacts = app.db().getGroupsFromDb(group.getId()).getContacts();
        assertThat(afterDeletionContacts, equalTo(contacts.without(deletedContact)));
    }

    private GroupData groupWithContacts (Contacts beforeContact, Groups groups) {
        for (ContactData contact : beforeContact) {
            if (contact.getGroups().size() > 0) {
                Groups groupsWithContacts = contact.getGroups();
                return groupsWithContacts.iterator().next();
            }
        }
        int nextId = app.getContactHelper().getNextId(beforeContact);
        app.getContactHelper().createContact(new ContactData().withName("Name 1").withFamily("Family 1").withAddress("Екатеринбург").withId(nextId));
        ContactData newContact = app.db().contacts().getContactById(app.db().contacts(), nextId);
        GroupData group = groups.iterator().next();
        app.getContactHelper().addContactToGroup(newContact, group);
        app.getContactHelper().getCurrentGroupPage(group);
        return group;
    }
}
