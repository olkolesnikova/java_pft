package pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod

    public void ensurePreconditions() {
        if (app.getContactHelper().all().size() == 0) {
            app.getContactHelper().createContact(new ContactData()
                    .withFamily("Соколов").withName("Олег").withAddress("Екатеринбург").withTelephone("4951251").withEmail("sokolov85@mail.ru").withGroup("test1"), true);
        }
    }

    @Test

    public void testContactPhones(){

        ContactData contact = app.getContactHelper().all().iterator().next();
        ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

        assertThat(contact.getTelephone(), equalTo(cleaned(contactInfoFromEditForm.getTelephone())));
        assertThat(contact.getMobile(), equalTo(cleaned(contactInfoFromEditForm.getMobile())));
        assertThat(contact.getWork(), equalTo(cleaned(contactInfoFromEditForm.getWork())));
    }

    public String cleaned(String phone){
        return  phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
