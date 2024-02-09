package ru.gb.Seminar2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingDummyPage {
    @FindBy(xpath = "//*[@id='upsert-item']/div[1]/label/input")
    WebElement firstNameField;
    @FindBy(xpath = "//*[@id='upsert-item']/div[5]/label/input")
    WebElement loginField;
    @FindBy(xpath = "//button[@type = 'submit']")
    WebElement saveButton;
    @FindBy(xpath = "//button[@data-mdc-dialog-action = 'close']")
    WebElement closeButton;

    public CreatingDummyPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createDummy(String firstName, String login) {
        firstNameField.sendKeys(firstName);
        loginField.sendKeys(login);
        saveButton.click();
        closeButton.click();
    }
}
