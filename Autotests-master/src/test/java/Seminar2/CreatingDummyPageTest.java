package Seminar2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.gb.Seminar2.CreatingDummyPage;
import ru.gb.Seminar2.LoginPage;
import ru.gb.Seminar2.MainPage;

import java.time.Duration;
import java.util.List;

public class CreatingDummyPageTest extends AbstractTest{
    String firstName = "DummiesName";
    String dummiesLogin = "DummiesLogin" + System.currentTimeMillis();
    @Test
    void createDummyTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(login, password);
        List<WebElement> webElementList = driver.findElements(By.partialLinkText("Hello"));
        Assertions.assertEquals(1, webElementList.size());

        Thread.sleep(400L);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        MainPage mainPage = new MainPage(driver);
        mainPage.addDummy();
        WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated((By.id("titleId-title"))));
        Assertions.assertEquals("Creating Dummy", title.getText());

        CreatingDummyPage creatingDummyPage = new CreatingDummyPage(driver);
        creatingDummyPage.createDummy(firstName, dummiesLogin);
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//tr/td[2][position() =1]")));
        Assertions.assertEquals("DummiesName", webElement.getText());


    }
}
