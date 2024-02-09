package ru.gb.Home3;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class UserTableRow {
    private final SelenideElement root;

    public UserTableRow(SelenideElement root) {
        this.root = root;
    }
    public String getID() {
        return root.$x("./td[1]").getText();
    }
    public String getName() {
        return root.$x("./td[2]").getText();
    }
    public String getStatus() {
        return root.$x("./td[4]").getText();
    }
    public void clickEditIcon(){
        root.$x("./td/button[text()='edit']").should(Condition.visible).click();
    }
    public void clickCredentials(){
        root.$x("./td/button[text()='key']").should(Condition.visible).click();
    }
    public void clickTrashIcon(){
        root.$x("./td/button[text()='delete']").should(Condition.visible).click();
    }

    public void clickRestoreFromTrashIcon(){
        root.$x("./td/button[text()='restore_from_trash']").should(Condition.visible).click();
    }


}
