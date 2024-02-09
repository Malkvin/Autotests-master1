package Seminar2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.gb.Seminar2.LoginPage;
import ru.gb.Seminar2.MainPage;
import ru.gb.Seminar2.UserTableRow;

import java.time.Duration;
import java.util.List;

public class MainPageTest extends AbstractTest{

    @Test
    void UserTableRowTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(login, password);
        List<WebElement> webElementList = driver.findElements(By.partialLinkText("Hello"));
        Assertions.assertEquals(1, webElementList.size());
        Thread.sleep(1000L);
        MainPage mainPage = new MainPage(driver);
        mainPage.addDummy();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated((By.id("titleId-title"))));
        Assertions.assertEquals("Creating Dummy", title.getText());
        mainPage.closeAddDummyWindow();

        List<WebElement> rowsWebElements = mainPage.getRowsInUserTable();
        List<UserTableRow> userTableRows = rowsWebElements
                .stream().map(UserTableRow::new).toList();
        UserTableRow searchRow = mainPage.getRowsByID("32850");
        String status = searchRow.getStatus();
        Assertions.assertEquals("active", status);
        searchRow.clickTrashIcon();
        String trashStatus = searchRow.getStatus();
        Assertions.assertEquals("inactive", trashStatus);

        searchRow.clickRestoreFromTrashIcon();
        String restoreStatus = searchRow.getStatus();
        Assertions.assertEquals("active", restoreStatus);


    }
}
