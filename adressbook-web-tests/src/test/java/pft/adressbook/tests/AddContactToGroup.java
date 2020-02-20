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
        if (app.db().contacts().size() == 0) {
            app.getContactHelper().createContact(new ContactData()
                    .withFamily("Соколов").withName("Олег").withAddress("Екатеринбург").withTelephone("4951251")
                    .withMobile("222").withWork("333").withEmail("sokolov85@mail.ru"));
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create (new GroupData().withName("test 1"));
        }

    }

    @Test

    public void testAddContactToGroup() {
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        ContactData addedContact = NewContact(before);
        app.getContactHelper().addContactToGroup(addedContact, group);
        Groups afterAdditionContact = app.db().getContactFromDb(addedContact.getId()).getGroups();
        assertThat(afterAdditionContact, equalTo(addedContact.getGroups().withAdded(group)));
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
