package Home1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

/*
1) логинимся под своими логином и паролем
2) нажимаем на ‘+’ для добавления dummy
3) вводим имя dummy и логин
4) нажимаем кнопку SAVE
5) проверяем, что dummy с именем появился в таблице
- достаточно проверить что появился нужный title
- закрывать модальное окно создания dummy не обязательно, таблица и так успешно прочитается

Требования и рекомендации:
- требуется корректно использовать явные ожидания: после логина, в момент открытия модального окна (оно может
не успеть), после сохранения dummy нужно дождаться появления искомого элемента
- предлагаем в конце теста написать сохранение скриншота окна браузера, достаточно сохранять просто в директорию
resources
- использовать в задании корректную структуру тестового класса, как минимум BeforeAll, BeforeEach, AfterEach
методы для создания, настройки и закрытия драйвера
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddDummyTest extends AbstractTest {
    String login = "GB202212c8b3e14";
    String password = "123d5e4487";
    String firstName = "DummiesName";
    String DummiesLogin = "DummiesLogin" + System.currentTimeMillis();

    @Test
    @DisplayName("Проверка успешной авторизации на стенде")
    @Order(1)
    void authorizationTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("form#login input[type='text']")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("form#login input[type='password']")
        ));
        WebElement loginButton = driver.findElement(By.xpath("//*[@form='login']"));
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        loginButton.click();
        List<WebElement> webElementList = driver.findElements(By.partialLinkText("Hello"));
        Assertions.assertEquals(1, webElementList.size());


    }

    @Test
    @DisplayName("Проверка успешного добавления dummy")
    @Order(2)
    void addDummyTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        WebElement plusButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@class='material-icons mdc-icon-button mdc-icon-button--display-flex mdc-ripple-upgraded--unbounded mdc-ripple-upgraded']")));
        plusButton.click();
        WebElement firstNameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id='upsert-item']/div[1]/label/input")));
        firstNameField.sendKeys(firstName);
        WebElement loginField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id='upsert-item']/div[5]/label/input")));
        loginField.sendKeys(DummiesLogin);
        WebElement saveButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[@type = 'submit']")
        ));
        saveButton.click();
        WebElement closeButton = driver.findElement(
                By.xpath("//button[@data-mdc-dialog-action = 'close']")
        );
        closeButton.click();
        WebElement webElementList = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//tr/td[2][position() =1]")));
        Assertions.assertEquals("DummiesName", webElementList.getText());
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(srcFile, new File("src/test/screenshots/screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
