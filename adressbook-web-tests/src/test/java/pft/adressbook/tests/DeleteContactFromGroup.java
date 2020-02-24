package pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.adressbook.model.*;

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
        Contacts allContacts = app.db().contacts();
        Groups allGroups = app.db().groups();
        GroupContact groupWithContact = groupWithContacts(allContacts, allGroups);
        GroupData group = groupWithContact.getGroup();
        Contacts contacts = group.getContacts();
        ContactData deletedContact = groupWithContact.getContact();
        app.getContactHelper().deleteGroupFromContact(deletedContact, group.getId());
        Contacts afterDeletionContacts = app.db().getGroupsFromDb(group.getId()).getContacts();
        assertThat(afterDeletionContacts, equalTo(contacts.without(deletedContact)));
    }

    private GroupContact groupWithContacts (Contacts allContacts, Groups allGroups) {
        for (ContactData contact : allContacts) {
            if (contact.getGroups().size() > 0) {
                GroupData group =  contact.getGroups().iterator().next();
                return new GroupContact().withGroupInContact(group).withContactInGroup(contact);
            }
        }
        GroupData group = allGroups.iterator().next();
        ContactData contact = allContacts.iterator().next();
        app.getContactHelper().returnToHomePage();
        app.getContactHelper().addContactToGroup(contact, group);
        app.getContactHelper().getCurrentGroupPage(group);
        return new GroupContact().withGroupInContact(group).withContactInGroup(contact);
    }
}
