package ru.gb.Home3;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("form#login input[type='text']");
    private SelenideElement passwordField = $("form#login input[type='password']");
    private SelenideElement loginButton = $("form#login button");
    public void authorization(String login, String password){
        loginField.should(Condition.visible).setValue(login);
        passwordField.should(Condition.visible).setValue(password);
        loginButton.should(Condition.visible).click();
    }
}
