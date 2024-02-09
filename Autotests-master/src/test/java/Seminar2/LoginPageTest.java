package Seminar2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.gb.Seminar2.LoginPage;

import java.util.List;

public class LoginPageTest extends AbstractTest {
    @Test
    void authorizationTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(login, password);
        List<WebElement> webElementList = driver.findElements(By.partialLinkText("Hello"));
        Assertions.assertEquals(1, webElementList.size());
    }
}
