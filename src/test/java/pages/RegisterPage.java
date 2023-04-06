package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegisterPage extends BasePage {

    By inputFirstName = By.id("customer.firstName");
    By inputLastName = By.id("customer.lastName");
    By inputAddress = By.id("customer.address.street");
    By inputCity = By.id("customer.address.city");
    By inputState = By.id("customer.address.state");
    By inputZipCode = By.id("customer.address.zipCode");
    By inputPhone = By.id("customer.phoneNumber");
    By inputSSN = By.id("customer.ssn");
    By inputUsername = By.id("customer.username");
    By inputPassword = By.id("customer.password");
    By inputPasswordConfirm = By.id("repeatedPassword");
    By menuRegistration = By.xpath("//a[normalize-space()='Register']");
    By bottomRegistration = By.xpath("//input[@value='Register']");
    By message = By.cssSelector("div[id='rightPanel'] p");

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
    }


    public String result() throws InterruptedException {
        Thread.sleep(2000);
        String res = findElement(message).getText();
        System.out.println("Page content result: " + res);
        return res;
    }

    public void clickRegistrationMenu() throws InterruptedException {
        click(menuRegistration);
    }

    public void completeForm(String name, String lastName, String address, String city, String state, String zipCode, String tel, String ssn, String username, String password, String confirmPassword) throws InterruptedException {
        writeInput(name, inputFirstName);
        writeInput(lastName, inputLastName);
        writeInput(address, inputAddress);
        writeInput(city, inputCity);
        writeInput(state, inputState);
        writeInput(zipCode, inputZipCode);
        writeInput(tel, inputPhone);
        writeInput(ssn, inputSSN);
        writeInput(username, inputUsername);
        writeInput(password, inputPassword);
        writeInput(confirmPassword, inputPasswordConfirm);
    }


    public void clickEnterBtn() throws InterruptedException {
        Thread.sleep(2000);
        click(bottomRegistration);
    }

}
