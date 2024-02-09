package ru.gb.Seminar3;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    private SelenideElement additionalInfoName = $x("//div[contains(text(), 'Full name')]/following-sibling::div");
    private SelenideElement underAvatarName = $x("//div[@class='mdc-typography--body2 smui-card__content']");

    public String getAdditionalInfoName() {
        return additionalInfoName.should(Condition.visible).getText();
    }
    public String getUnderAvatarName() {
        return underAvatarName.should(Condition.visible).getText();
    }
}
