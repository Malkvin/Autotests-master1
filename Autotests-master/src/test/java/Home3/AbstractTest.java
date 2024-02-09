package Home3;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.gb.Home3.LoginPage;

public abstract class AbstractTest {
    public static LoginPage loginPage;

    @BeforeEach
    public void init() {
        Selenide.open("https://test-stand.gb.ru/login");
        loginPage = Selenide.page(LoginPage.class);
    }

    @AfterEach
    public void close() {
        WebDriverRunner.closeWebDriver();
    }
}
