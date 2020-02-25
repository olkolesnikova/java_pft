package pft.mantis.appmanager;

import org.openqa.selenium.By;
import pft.mantis.tests.TestBase;

import java.io.UnsupportedEncodingException;

public class LoginAdminHelper extends HelperBase{

        public LoginAdminHelper(ApplicationManager app) {
            super(app);
        }

    public void loginAdmin(String username, String password, int userId) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        click(By.xpath("//input[@value='Войти']"));
        type(By.name("password"), password);
        click(By.xpath("//input[@value='Войти']"));
        String xpath = String.format("//a[@href='manage_user_edit_page.php?user_id=%s']", userId);
        click(By.xpath(xpath));
        click(By.linkText("Управление пользователями"));
        click(By.linkText("user1582570475822"));
        click(By.xpath("//input[@value='Сбросить пароль']"));
    }
}




