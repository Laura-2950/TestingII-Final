package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import extetReports.ExtentFactory;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Account;

import utils.Customer;

import java.util.List;

import static io.restassured.RestAssured.given;


public class TestBackEnd {
    private String baseURI = "https://parabank.parasoft.com/parabank/services/bank";
    private String username = "MarisHawkins";
    private String password = "HMN58XGM9ZG";
    private static ExtentSparkReporter spark = new ExtentSparkReporter("target/SparkBackEnd.html");
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeTest
    static void report() {
        extent = ExtentFactory.getInstance("Rest Assured", "4.3.3");
        extent.attachReporter(spark);
    }


    @Test(priority = 1)
    @Tag("Back-End")
    public void testGetRegisterPage() {
        test = extent.createTest("Proceso de Registro.");
        test.log(Status.INFO, "Inicia el test...");

        given()
                .when()
                .get("https://parabank.parasoft.com/parabank/register.htm")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .log()
                .status();
        test.log(Status.PASS, "Se verifica el status 200 en la respuesta.");
    }

    @Test(priority = 2)
    @Tag("Back-End")
    public void testLogin() {
        test = extent.createTest("Proceso de ligin.");
        test.log(Status.INFO, "Inicia el test...");
        Customer customer =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get(baseURI + "/login/" + username + "/" + password)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .log()
                        .status().extract().as(Customer.class);

        System.out.println(customer);
        test.log(Status.PASS, "Usuario logueado exitosamente.");

    }

    @Test(priority = 3)
    @Tag("Back-End")
    public void testViewCustomerAccounts() {
        test = extent.createTest("Proceso de obtener información de las cuentas del usuario.");
        test.log(Status.INFO, "Inicia el test...");

        Customer customer =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get(baseURI + "/login/" + username + "/" + password)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .log()
                        .status().extract().as(Customer.class);

        System.out.println(customer);
        test.log(Status.PASS, "Usuario logueado exitosamente con el ID: " + customer.getId());
        test.log(Status.INFO, "Efectuamos la petición para obtener el de detalle de las cuentas del usuario...");

        Response res =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .auth().basic(username, password)
                        .get(baseURI + "/customers/" + customer.getId() + "/accounts")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .log()
                        .status().extract().response();

        XmlPath xmlObj = new XmlPath(res.asString());
        List<String> accounts = xmlObj.getList("accounts.account");

        test.log(Status.PASS, "Obtenemos el detalle de las cuentas: " + accounts);

        System.out.println(accounts);

    }


    @Test(priority = 4)
    @Tag("Back-End")
    public void testCreateNewAccount() {
        test = extent.createTest("Abrir una nueva cuenta.");
        test.log(Status.INFO, "Inicia el test...");

        Customer customer =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get(baseURI + "/login/" + username + "/" + password)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .log()
                        .status().extract().as(Customer.class);

        System.out.println(customer);
        test.log(Status.PASS, "Usuario logueado exitosamente con el ID: " + customer.getId());

        Response res =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .auth().basic(username, password)
                        .get(baseURI + "/customers/" + customer.getId() + "/accounts")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .log()
                        .status().extract().response();

        XmlPath xmlObj = new XmlPath(res.asString());
        List<String> accountsId = xmlObj.getList("accounts.account.id");


        System.out.println(accountsId);

        String myCustomerId = customer.getId();
        String firstAccountId = xmlObj.get("accounts.account[0].id");
        String accountType = "1";

        test.log(Status.INFO, "Efectuamos la petición para la creación de una nueva cuenta para el usuario...");

        Account account =
                given()
                        .contentType(ContentType.JSON).with()
                        .queryParam("customerId", myCustomerId)
                        .queryParam("newAccountType", accountType)
                        .queryParam("fromAccountId", firstAccountId)
                        .auth().basic(username, password)
                        .when()
                        .post(baseURI + "/createAccount")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .log()
                        .status().extract().as(Account.class);

        System.out.println(account);

        test.log(Status.PASS, "Nueva cuenta creada exitosamente con el ID: " + account.getId());

    }

    @Test(priority = 5)
    @Tag("Back-End")
    public void testSummaryOfAccount() {
        test = extent.createTest("Resumen de las cuentas.");
        test.log(Status.INFO, "Inicia el test...");
        given()
                .when()
                .get("https://parabank.parasoft.com/parabank/overview.htm")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .log()
                .status();
        test.log(Status.PASS, "Se verifica el status 200 en la respuesta.");

    }

    @Test(priority = 6)
    @Tag("Back-End")
    public void testSummaryOfAccountSwagger() {

        test = extent.createTest("Resumen de las cuentas consultado desde los endpoints de la APi.");
        test.log(Status.INFO, "Inicia el test...");
        Customer customer =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get(baseURI + "/login/" + username + "/" + password)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .log()
                        .status().extract().as(Customer.class);

        System.out.println(customer);
        test.log(Status.PASS, "Usuario logueado exitosamente con el ID: " + customer.getId());
        test.log(Status.INFO, "Efectuamos la petición para obtener el de detalle de las cuentas del usuario...");


        Response res =
                given()
                        .contentType(ContentType.JSON)
                        .auth().basic(username, password)
                        .when()
                        .get(baseURI + "/customers/" + customer.getId() + "/accounts")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .log()
                        .status().extract().response();
        XmlPath xmlObj = new XmlPath(res.asString());
        List<String> accountsId = xmlObj.getList("accounts.account");
        System.out.println(accountsId);
        test.log(Status.PASS, "Obtenemos el detalle de las cuentas: " + accountsId);

    }

    @Test(priority = 7)
    @Tag("Back-End")
    public void testDownloadFunds() {
        test = extent.createTest("Descarga de fondos.");
        test.log(Status.INFO, "Inicia el test...");

        Customer customer =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get(baseURI + "/login/" + username + "/" + password)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .log()
                        .status().extract().as(Customer.class);

        System.out.println(customer);
        test.log(Status.PASS, "Usuario logueado exitosamente con el ID: " + customer.getId());

        Response res =
                given()
                        .contentType(ContentType.JSON)
                        .auth().basic(username, password)
                        .when()
                        .get(baseURI + "/customers/" + customer.getId() + "/accounts")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .log()
                        .status().extract().response();
        XmlPath xmlObj = new XmlPath(res.asString());
        List<String> accountsId = xmlObj.getList("accounts.account.id");
        System.out.println(accountsId);

        String firstAccountId = accountsId.get(0);
        String newAccountId = accountsId.get(1);

        test.log(Status.INFO, "Efectuamos la petición para poder efectuar transferencias...");

        String amount = "200";
        given()
                .contentType(ContentType.JSON).with()
                .queryParam("fromAccountId", firstAccountId)
                .queryParam("toAccountId", newAccountId)
                .queryParam("amount", amount)
                .auth().basic(username, password)
                .when()
                .post(baseURI + "/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .log()
                .body();
        test.log(Status.PASS, "Transferencia exitosa desde la cuenta ID: " + firstAccountId + " hacia la cuenta con ID: " + newAccountId + " por el monto de: " + amount);


    }

    @Test(priority = 8)
    @Tag("Back-End")
    public void testAccountActivity() {
        test = extent.createTest("Actividad de la cuenta.");
        test.log(Status.INFO, "Inicia el test...");


        Customer customer =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get(baseURI + "/login/" + username + "/" + password)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .log()
                        .status().extract().as(Customer.class);

        System.out.println(customer);
        test.log(Status.PASS, "Usuario logueado exitosamente con el ID: " + customer.getId());


        Response res =
                given()
                        .contentType(ContentType.JSON)
                        .auth().basic(username, password)
                        .when()
                        .get(baseURI + "/customers/" + customer.getId() + "/accounts")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .log()
                        .status().extract().response();
        XmlPath xmlObj = new XmlPath(res.asString());
        List<String> accountsId = xmlObj.getList("accounts.account.id");
        System.out.println(accountsId);
        test.log(Status.INFO, "Efectuamos la petición para conocer las transferencias realizadas en cuenta, fecha y de tipo determinada...");

        String all = "All";
        Response resActivity =
                given()
                        .contentType(ContentType.JSON)
                        .auth().basic(username, password)
                        .when()
                        .get(baseURI + "/accounts/" + accountsId.get(0) + "/transactions/month/" + all + "/type/" + all)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .log()
                        .status().extract().response();

        XmlPath xmlObj2 = new XmlPath(resActivity.asString());
        List<String> transactionsId = xmlObj2.getList("transactions.transaction.id");
        System.out.println(transactionsId);
        test.log(Status.PASS, "Obtenemos el detalle de las transferencias con ID: " + transactionsId);


    }

    @AfterTest
    public void tearDown() {
        extent.flush();
    }
}
