package ru.gb.Home3;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private ElementsCollection rowsInUserTable = $$x("//table[@aria-label='Dummies list']/tbody/tr");
    private SelenideElement plusButton = $(By.id("create-btn"));
    private SelenideElement closeButton = $x("//button[@data-mdc-dialog-action='close']");
    private SelenideElement userNameLinkInNavBar = $("nav li.mdc-menu-surface--anchor a");
    private SelenideElement firstNameField = $x("//*[@id='upsert-item']/div[1]/label/input");
    private SelenideElement loginField = $x("//*[@id='upsert-item']/div[5]/label/input");
    private SelenideElement saveButton = $x("//button[@type = 'submit']");
    private SelenideElement modalWindow = $x("//div[@id='contentId-content']");
    private SelenideElement profileButton = $x("//li[@role='menuitem']//span[text()='Profile']");


    public void addDummy(String firstName, String dummiesLogin) {
        plusButton.should(Condition.visible).click();
        modalWindow.should(Condition.visible).isDisplayed();
        firstNameField.should(Condition.visible).setValue(firstName);
        loginField.should(Condition.visible).setValue(dummiesLogin);
        saveButton.should(Condition.visible).click();
        closeButton.should(Condition.visible).click();
    }

    public ElementsCollection getRowsInUserTable() {
        return rowsInUserTable.should(CollectionCondition.sizeGreaterThan(0));
    }

    public UserTableRow getRowsByID(String ID) {
        return rowsInUserTable
                .asDynamicIterable()
                .stream()
                .map(UserTableRow::new)
                .filter(row -> row.getID().equals(ID))
                .findFirst().orElseThrow();
    }
    public UserTableRow getRowsByName(String name) {
        return rowsInUserTable
                .asDynamicIterable()
                .stream()
                .map(UserTableRow::new)
                .filter(row -> row.getName().equals(name))
                .findFirst().orElseThrow();
    }

    public String getUsernameLabelText() {
        return userNameLinkInNavBar.should(Condition.visible).getText().replace("\n", " ");
    }

    public SelenideElement waitAndGetDummyNameByText(String title) {
        String xpath = String.format("//table[@aria-label='Dummies list']/tbody//td[text()='%s']", title);
        return $x(xpath).should(Condition.visible);
    }
    public void clickProfileButton(){
        userNameLinkInNavBar.should(Condition.visible).click();
        profileButton.should(Condition.visible).click();
    }
}
