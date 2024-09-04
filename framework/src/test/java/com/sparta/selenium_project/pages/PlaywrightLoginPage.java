package com.sparta.selenium_project.pages;

import com.sparta.selenium_project.utils.TestContext;
import org.openqa.selenium.By;

public class PlaywrightLoginPage {
    private final TestContext context;

    public PlaywrightLoginPage(TestContext context) {
        this.context = context;
    }

    public void enterUsername(String username){
        context.page.fill("#user-name",username);
    }

    public void enterPassword(String password){
        context.page.fill("#password",password);
    }

    public void login(){
        context.page.click("#login-button");
    }

    public String getErrorMessage(){
        return context.page.locator("text=Epic sadface").textContent();
    }
}
