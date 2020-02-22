package pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;
import pft.adressbook.model.Contacts;
import pft.adressbook.model.GroupData;
import pft.adressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddContactToGroup extends TestBase {

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

    public void testAddContactToGroup() {
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        ContactData addedContact = newContact(before);
        app.getContactHelper().addContactToGroup(addedContact, group);
        Groups afterAdditionContact = app.db().getContactFromDb(addedContact.getId()).getGroups();
        assertThat(afterAdditionContact, equalTo(addedContact.getGroups().withAdded(group)));
    }

    private ContactData newContact(Contacts before) {
        for (ContactData contact : before) {
            if (contact.getGroups().size() < app.db().groups().size()) {
                return contact;
            }
        }
        int nextId = app.getContactHelper().getNextId(before);
        app.getContactHelper().createContact(new ContactData().withName("111").withFamily("222").withAddress("333").withId(nextId));
        ContactData newContact = app.db().contacts().getContactById(app.db().contacts(), nextId);
        return newContact;
    }
}
