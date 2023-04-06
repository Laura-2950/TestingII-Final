package test;

import base.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import extetReports.ExtentFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.TransferFundsPage;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class Test3TransferFunds extends BasePage {

    private TransferFundsPage transferFundsPage;
    private WebDriver driver;
    static ExtentSparkReporter spark = new ExtentSparkReporter("target/SparkFrontEnd3.html");
    static ExtentReports extent;
    ExtentTest test;

    @BeforeAll
    static void report() {
        extent = ExtentFactory.getInstance("Selenium version", "4.0");
        extent.attachReporter(spark);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver = super.chromeDriverConnection();
        transferFundsPage = new TransferFundsPage(driver, null);
        transferFundsPage.link("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test
    @Tag("Front-End")
    public void successTransfer() throws InterruptedException {
        test = extent.createTest("Transferencia de fondos.");
        test.log(Status.INFO, "Inicia el test...");

        transferFundsPage.loguin("MarisHawkins", "HMN58XGM9ZG");
        test.log(Status.INFO, "Iniciamos sesión con el usuario de prueba.");

        transferFundsPage.clickTransferFundsMenu();

        test.log(Status.PASS, "Nos dirigimos a la página para efectuar transferencias.");

        assertTrue(transferFundsPage.checkTitle().equals("Transfer Funds"));
        test.log(Status.PASS, "Comprobamos estar en la página para efectuar transferencias si el título \"Transferir fondos\" es visible en la pantalla.");

        transferFundsPage.completeForm("100");
        transferFundsPage.clickEnterBtn();

        assertTrue(transferFundsPage.result().equals("Transfer Complete!"));

        test.log(Status.PASS, "Completamos los campos y efectuamos una tranferencia y comprobamos que el texto \"¡Transferencia completa!\" es visible en la pantalla.");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        extent.flush();
    }
}
