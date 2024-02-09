package ru.gb.Home3;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class EditingDummyPage {
    private SelenideElement firstNameField = $x("//*[@id='upsert-item']/div[1]/label/input");
    private SelenideElement saveButton =$x("//button[@type = 'submit']");
    private SelenideElement closeButton =$x("//button[@data-mdc-dialog-action = 'close']");



    public void editeDummy(String firstName) {
        firstNameField.should(Condition.visible).click();
        firstNameField.should(Condition.visible).clear();
        firstNameField.should(Condition.visible).setValue(firstName);
        saveButton.should(Condition.visible).click();
        closeButton.should(Condition.visible).click();

    }
}
