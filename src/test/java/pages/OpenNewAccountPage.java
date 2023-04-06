package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenNewAccountPage extends BasePage {

    By inputUsername = By.xpath("//input[@name='username']");
    By inputPassword = By.xpath("//input[@name='password']");
    By btnLogIn = By.xpath("//input[@value='Log In']");
    By menuOpenNewAccount = By.xpath("//a[normalize-space()='Open New Account']");
    By selectTypeAccount = By.id("type");
    By btnOpenNewAccount = By.xpath("//input[@value='Open New Account']");
    By message = By.cssSelector("body > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > p:nth-child(2)");

    public OpenNewAccountPage(WebDriver driver, WebDriverWait wait) {
    }

    public String result() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(message));
        WebElement element = driver.findElement(message);
        String res = element.getText();
        System.out.println("Page content result: " + res);
        return res;
    }

    public void clickOpenNewAccountMenu() throws InterruptedException {
        click(menuOpenNewAccount);
    }

    public void selectAccountType() throws InterruptedException {
        Select select = new Select(driver.findElement(selectTypeAccount));
        select.selectByVisibleText("SAVINGS");
    }

    public void clickEnterBtn() throws InterruptedException {
        Thread.sleep(2000);
        //wait.until(ExpectedConditions.presenceOfElementLocated(btnOpenNewAccount));
        click(btnOpenNewAccount);
    }

    public void loguin(String username, String password) throws InterruptedException {
        writeInput(username, inputUsername);
        writeInput(password, inputPassword);
        click(btnLogIn);
    }

}
