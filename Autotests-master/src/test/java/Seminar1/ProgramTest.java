package Seminar1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProgramTest {
    static WebDriver driver;
    String login = "MBanks";
    String password = "ad24957d6e";

    @BeforeAll
    static void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://test-stand.gb.ru/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @Disabled
    void test() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
//        options.addArguments("--headless"); //чтобы не запускалось окно браузера
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://test-stand.gb.ru/login");
        Thread.sleep(5000L);
        driver.quit();
    }

    @Test
    void autorization(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement loginField = driver.findElement(By.xpath("//*[@type='text']"));
//        WebElement passwordField = driver.findElement(By.xpath("//*[@type='password']"));
        WebElement loginField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("form#login input[type='text']")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("form#login input[type='password']")
        ));
        WebElement loginButton = driver.findElement(By.xpath("//*[@form='login']"));
//        WebElement usernameField = driver.findElement(By.cssSelector("form#login input[type='text']"));
//        WebElement passwordField = driver.findElement(By.cssSelector("form#login input[type='password']"));
//        WebElement loginButton = driver.findElement(By.cssSelector("form#login button"));
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        loginButton.click();
        List<WebElement> webElementList = driver.findElements(By.partialLinkText("Hello"));
        Assertions.assertEquals(1, webElementList.size());
    }

    @AfterAll
    static void close() {
        driver.quit();
    }
}
