package pft.adressbook.tests;

import org.testng.annotations.Test;
import pft.adressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

    @Test

    public void testGroupModification() {

        app.getNavigationHelper().gotoGroupPage("groups");
        app.getGroupHelper().selectGroup("selected[]");
        app.getGroupHelper().initGroupModifacation();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage("group page");
    }


}
