package test;

import base.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import extetReports.ExtentFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.AccountsOverviewPage;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class Test4AccountsOverview extends BasePage {

    private AccountsOverviewPage accountsOverviewPage;
    private WebDriver driver;
    static ExtentSparkReporter spark = new ExtentSparkReporter("target/SparkFrontEnd4.html");
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
        accountsOverviewPage = new AccountsOverviewPage(driver, null);
        accountsOverviewPage.link("https://parabank.parasoft.com/parabank/index.htm");
    }


    @Test()
    @Tag("Front-End")
    public void viewingAccountDetails() throws InterruptedException {
        test = extent.createTest("Visualizando la actividad de la cuenta.");
        test.log(Status.INFO, "Inicia el test...");

        accountsOverviewPage.loguin("MarisHawkins", "HMN58XGM9ZG");
        test.log(Status.INFO, "Iniciamos sesión con el usuario de prueba.");

        accountsOverviewPage.clickAccountOverview();

        test.log(Status.PASS, "Abrimos la página para visualizar las cuentas del usuario.");

        assertTrue(accountsOverviewPage.checkTitle().equals("*Balance includes deposits that may be subject to holds"));
        test.log(Status.PASS, "Comprobamos que el texto \"*El saldo incluye depósitos que pueden estar sujetos a retenciones\" es visible en la pantalla.");

        accountsOverviewPage.selectAccount();
        assertTrue(accountsOverviewPage.checkTitleAccountDetails().equals("Account Details"));
        test.log(Status.PASS, "Ingresamos al detalle de una cuenta en particular y comprobamos que el título \"Account Details\" es visible en la pantalla.");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        extent.flush();
    }
}
