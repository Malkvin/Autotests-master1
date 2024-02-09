package Home3;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.gb.Home3.EditingDummyPage;
import ru.gb.Home3.MainPage;
import ru.gb.Home3.ProfilePage;
import ru.gb.Home3.UserTableRow;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GBStandTest extends AbstractTest {
    private static final String LOGIN = "GB202212c8b3e14";
    private static final String PASSWORD = "123d5e4487";
    private static final String ADMINNAME = "GB202212 c8b3e14";
    private MainPage mainPage;
    private ProfilePage profilePage;
    private EditingDummyPage editingDummyPage;


    @Test
    @DisplayName("Тест на ошибку при пустых полях LOGIN и PASSWORD")
    void textErrorLoginTest() {
        String emptyLogin = "";
        String emptyPass = "";
        loginPage.authorization(emptyLogin, emptyPass);
        SelenideElement errorText = $x("//*[@class = 'error-block svelte-uwkxn9']");
        Assertions.assertEquals("401\nInvalid credentials.", errorText.getText());
    }

    @Test
    @DisplayName("Тест на успешную авторизацию")
    void authorizationTest() {
        checkLogin();
    }

    private void checkLogin() {
        loginPage.authorization(LOGIN, PASSWORD);
        mainPage = Selenide.page(MainPage.class);
        assertTrue(mainPage.getUsernameLabelText().contains(LOGIN));
    }

    @Test
    @DisplayName("Проверка успешного добавления dummy")
    void addDummyTest() {
        checkLogin();
        String firstName = "DummiesName" + System.currentTimeMillis();
        String dummiesLogin = "DummiesLogin" + System.currentTimeMillis();
        mainPage.addDummy(firstName, dummiesLogin);
        // Проверка, что dummy создан и находится в таблице
        assertTrue(mainPage.waitAndGetDummyNameByText(firstName).isDisplayed());
    }

    @Test
    @DisplayName("Проверка редактирования dummy")
    void EditDummyTest() {
        checkLogin();
        ElementsCollection rowsWebElements = mainPage.getRowsInUserTable();
        List<UserTableRow> userTableRows = rowsWebElements.asDynamicIterable()
                .stream().map(UserTableRow::new).toList();
        UserTableRow searchRow = mainPage.getRowsByID("33107");
        searchRow.clickEditIcon();
        editingDummyPage = Selenide.page(EditingDummyPage.class);
        String newName = "NewName" + System.currentTimeMillis();
        editingDummyPage.editeDummy(newName);
        assertTrue(mainPage.waitAndGetDummyNameByText(newName).isDisplayed());
    }

    @Test
    @DisplayName("Проверка окна Credentials")
    void CheckCredentialsTest() {
        checkLogin();
        ElementsCollection rowsWebElements = mainPage.getRowsInUserTable();
        List<UserTableRow> userTableRows = rowsWebElements.asDynamicIterable()
                .stream().map(UserTableRow::new).toList();
        UserTableRow searchRowForKey = mainPage.getRowsByID("33108");
        searchRowForKey.clickCredentials();
        SelenideElement title = $(By.id("simple-title")).should(Condition.visible);
        Assertions.assertEquals("Dummy credentials", title.getText());
        SelenideElement content = $(By.id("simple-content")).should(Condition.visible);
        Assertions.assertEquals("Login: DummiesLogin1707376453286\n" +
                "PW: ad37fef9a2", content.getText());

    }
    @Test
    @DisplayName("Проверка страницы Profile")
    public void profilePageTest(){
        checkLogin();
        mainPage.clickProfileButton();
        profilePage = Selenide.page(ProfilePage.class);
        Assertions.assertEquals(ADMINNAME, profilePage.getAdditionalInfoName());
        Assertions.assertEquals(ADMINNAME, profilePage.getUnderAvatarName());
    }
    @Test
    @DisplayName("Проверка смены статуса")
    public void changeStatusTest(){
        checkLogin();
        ElementsCollection rowsWebElements = mainPage.getRowsInUserTable();
        List<UserTableRow> userTableRows = rowsWebElements.asDynamicIterable()
                .stream().map(UserTableRow::new).toList();
        UserTableRow searchRow = mainPage.getRowsByName("Hanna");
        String status = searchRow.getStatus();
        Assertions.assertEquals("active", status);
        searchRow.clickTrashIcon();
        Selenide.sleep(2000L);
        String trashStatus = searchRow.getStatus();
        Assertions.assertEquals("inactive", trashStatus);
        searchRow.clickRestoreFromTrashIcon();
        Selenide.sleep(2000L);
        String restoreStatus = searchRow.getStatus();
        Assertions.assertEquals("active", restoreStatus);
    }

}
