# Proyecto Final
## Testing II

#### Objetivo
Crear un proyecto completo de Pruebas Automatizadas usando Java y Selenium, con Suites, Etiquetas e Informes, 
validando también una API RestAssured. 
#### Sistema bajo prueba:
Para [Bank](https://parabank.parasoft.com/parabank/index.htm)

Documentación:
- [Services](https://parabank.parasoft.com/parabank/services.htm)
- [Swagger](https://parabank.parasoft.com/parabank/api-docs/index.html)
- [Admin Page](https://parabank.parasoft.com/parabank/admin.htm)


#### Título del caso de prueba:
Proceso de registro, apertura de una nueva cuenta, visión general de la cuenta, 
transferencia de fondos, actividad de la cuenta, para todas las etapas de las pruebas frontales

#### Pruebas Front-End:

● Registro

    ○ Haga clic en <Registro>
    ○ Rellene el formulario de registro con los datos requeridos
    ○ Pulse de nuevo en <Registro>. 
    ○ Compruebe que el texto "Su cuenta se ha creado correctamente. En la pantalla se puede ver "You are now logged in".
● Abrir una nueva cuenta

    ○ Haga clic en <Abrir nueva cuenta>.
    ○ En el apartado "¿Qué tipo de cuenta desea abrir?" seleccione la opción <SAVINGS>.
    ○ Haga clic en <Abrir nueva cuenta> 
    ○ Compruebe si el texto "Congratulations, your account is now open." está visible en la pantalla.

● Transferir Fondos

    ○ Haga clic en <Transferencia de fondos>
    ○ Compruebe que el texto "Transferir fondos" es visible en la pantalla
    ○ En el campo <Importe: $> introduzca el importe a transferir
    ○ En el campo <De la cuenta #> seleccione una cuenta
    ○ En el campo <a la cuenta #> seleccione una cuenta distinta a la anterior
    ○ Haga clic en <Transferencia>
    ○ Compruebe que el texto "¡Transferencia completa!" es visible en la pantalla
● Actividad de la cuenta (cada mes)

    ○ Haga clic en <Resumen de cuentas>
    ○ Compruebe que el texto "*El saldo incluye depósitos que pueden estar sujetos a retenciones" es visible en la pantalla
    ○ Haga clic en una cuenta en la columna <Cuenta>. 
    ○ Compruebe que el texto "Detalles de la cuenta" es visible en la pantalla

#### Prueba Back-End de la API Rest:
● Validación del código de estado 200

    ○ Registro URL: https://parabank.parasoft.com/parabank/register.htm
    ○ Abrir una nueva cuenta URL: https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?customerId=12545&newAccountType=1&fromAccountId=xxxxx
    ○ Resumen de las cuentas URL: https://parabank.parasoft.com/parabank/overview.htm
    ○ Descarga de fondos URL: https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId=13566&toAccountId=13677&amount=xxxxx
    ○ Actividad de la cuenta (cada mes) URL: https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/13566/transactions/month/All/type/All

No es necesario introducir datos reales en los formularios durante la prueba. Podemos utilizar, 
por ejemplo, un generador de datos: https://generatedata.com

#### Informes con Extent:

- [Front End Test 1](http://localhost:63342/PageObjectModel/target/SparkFrontEnd1.html)
- [Front End Test 2](http://localhost:63342/PageObjectModel/target/SparkFrontEnd2.html)
- [Front End Test 3](http://localhost:63342/PageObjectModel/target/SparkFrontEnd3.html)
- [Front End Test 4](http://localhost:63342/PageObjectModel/target/SparkFrontEnd4.html)
- [Back End](http://localhost:63342/PageObjectModel/target/SparkBackEnd.html)

#### Se adjunta collección de Postman Final-Testing II.postman_collection.jsn