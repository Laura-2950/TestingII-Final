package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountsOverviewPage extends BasePage {

    By inputUsername = By.xpath("//input[@name='username']");
    By inputPassword = By.xpath("//input[@name='password']");
    By btnLogIn = By.xpath("//input[@value='Log In']");
    By menuAccountOverview = By.xpath("//a[normalize-space()='Accounts Overview']");
    By title = By.cssSelector("td[colspan='3']");
    By account = By.xpath("//a[normalize-space()='13677']");
    By titleAccountDetails = By.xpath("//h1[normalize-space()='Account Details']");

    public AccountsOverviewPage(WebDriver driver, WebDriverWait wait) {
    }

    public String checkTitleAccountDetails() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(titleAccountDetails));
        WebElement element = driver.findElement(titleAccountDetails);
        String res = element.getText();
        System.out.println("Page content result: " + res);
        return res;
    }

    public String checkTitle() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(title));
        WebElement element = driver.findElement(title);
        String res = element.getText();
        System.out.println("Page content title: " + res);
        return res;
    }

    public void clickAccountOverview() throws InterruptedException {
        click(menuAccountOverview);
    }

    public void selectAccount() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(account));
        WebElement element = driver.findElement(account);
        click(account);
    }

    public void loguin(String username, String password) throws InterruptedException {
        writeInput(username, inputUsername);
        writeInput(password, inputPassword);
        click(btnLogIn);
    }
}
