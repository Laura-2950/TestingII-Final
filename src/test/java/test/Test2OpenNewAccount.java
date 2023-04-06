package test;

import base.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import extetReports.ExtentFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.OpenNewAccountPage;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class Test2OpenNewAccount extends BasePage {

    private OpenNewAccountPage openNewAccountPage;
    private WebDriver driver;
    static ExtentSparkReporter spark = new ExtentSparkReporter("target/SparkFrontEnd2.html");
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
        openNewAccountPage = new OpenNewAccountPage(driver, null);
        openNewAccountPage.link("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test
    @Tag("Front-End")
    public void successNewAccount() throws InterruptedException {
        test = extent.createTest("Apertura de una nueva cuenta.");
        test.log(Status.INFO, "Inicia el test...");

        openNewAccountPage.loguin("MarisHawkins", "HMN58XGM9ZG");
        test.log(Status.INFO, "Iniciamos sesión con el usuario de prueba.");

        openNewAccountPage.clickOpenNewAccountMenu();

        test.log(Status.PASS, "Nos dirigimos a la página para crear una nueva cuenta para el usuario.");

        openNewAccountPage.selectAccountType();

        test.log(Status.PASS, "Seleccionamos el tipo de cuenta <SAVINGS>.");

        openNewAccountPage.clickEnterBtn();

        assertTrue(openNewAccountPage.result().contains("Congratulations, your account is now open."));

        test.log(Status.PASS, "Verificamos la correcta creación de la nueva cuenta si el mensaje \"Congratulations, your account is now open.\" es visible en la pantalla..");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        extent.flush();
    }
}
