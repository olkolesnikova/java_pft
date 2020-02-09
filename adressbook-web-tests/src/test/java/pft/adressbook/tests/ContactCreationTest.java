package pft.adressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pft.adressbook.model.ContactData;
import pft.adressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTest extends TestBase {


    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null){
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null){
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> groups = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType());
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();

    }

    @Test(dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) throws Exception {
        Contacts before = app.getContactHelper().all();
        app.getContactHelper().gotoNewContactPage();
        File photo = new File("src/test/resources/volk.jpg");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size() + 1));
        Contacts after = app.getContactHelper().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }


}
