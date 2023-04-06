package test;

import base.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import extetReports.ExtentFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class Test1Register extends BasePage {

    private RegisterPage registerPage;
    private WebDriver driver;
    static ExtentSparkReporter spark = new ExtentSparkReporter("target/SparkFrontEnd1.html");
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
        registerPage = new RegisterPage(driver, null);
        registerPage.link("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test
    @Tag("Front-End")
    public void successfulRegistration() throws InterruptedException {
        test = extent.createTest("Proceso de registro.");
        test.log(Status.INFO, "Inicia el test...");

        registerPage.clickRegistrationMenu();

        test.log(Status.PASS, "Abrir página de registro de usuario.");

        registerPage.completeForm("Maris",
                "Hawkins",
                "8776 Parturient Street",
                "Jeju",
                "Australia",
                "315598",
                "(747) 933-4057",
                "616616516516",
                "MarisHawkins",
                "HMN58XGM9ZG",
                "HMN58XGM9ZG");

        test.log(Status.PASS, "Completar el formulario de registración.");

        registerPage.clickEnterBtn();

        assertTrue(registerPage.result().equals("Your account was created successfully. You are now logged in."));

        test.log(Status.PASS, "Verificar usuario.");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        extent.flush();
    }

}
