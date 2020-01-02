package pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pft.adressbook.model.GroupData;

public class GroupHelper {
    private WebDriver wd;

    public GroupHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void returnToGroupPage(String s) {
      wd.findElement(By.linkText(s)).click();
    }

    public void submitGroupCreation(String submit) {
      wd.findElement(By.name(submit)).click();
    }

    public void fillGroupForm(GroupData groupData) {
      wd.findElement(By.name("group_name")).click();
      wd.findElement(By.name("group_name")).clear();
      wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
      wd.findElement(By.name("group_header")).clear();
      wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
      wd.findElement(By.name("group_footer")).click();
      wd.findElement(By.name("group_footer")).clear();
      wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    public void initGroupCreation(String s) {
      wd.findElement(By.name(s)).click();
    }

    public void deleteSelectedGroups(String delete) {
      wd.findElement(By.name(delete)).click();
    }

    public void selectGroup(String s) {
      wd.findElement(By.name(s)).click();
    }
}
