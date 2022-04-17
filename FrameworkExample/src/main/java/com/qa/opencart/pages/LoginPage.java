package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    //1. private By locators:
    private By emailId= By.id("input-email");
    private By password= By.id("input-password");
    private By loginBtn= By.xpath("//input[@value='Login']");
    private By forgotPwd= By.linkText("Forgotten Password");


  //2. Public constructor
    public LoginPage(WebDriver dfiver){
        this.driver =driver;
    }

    //3. public page actions:
    public String getLoginPageTitle(){
        return driver.getTitle();
    }
    public String getLoginPageUrl(){
        return driver.getCurrentUrl();
    }
    public boolean isForgorPasswordLinkExist(){
        return driver.findElement(forgotPwd).isDisplayed();
    }
    public void doLogin(String un,String pass){
        driver.findElement(emailId).sendKeys(un);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }
}
