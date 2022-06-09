package StepDefinition;

import Authorization.Authorization;
import Configuracion.ConfVariables;
import Request.BuscarDireccionRequest;
import Request.BuscarIdCuentaRequest;
import Response.BuscarDireccionResponse;
import Response.BuscarIdCuentaResponse;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class GetConsolidated {

    String idCuenta;

    @Given("solicito accesos al portal de QA")
    public  void setup(){
        RestAssured.baseURI= ConfVariables.getHost();
        RestAssured.basePath="/back";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
               //.auth()
               //.oauth2(new Authorization().Auth());
    }
    @Then("Consulta de servico de consolidado")
    public void consultaConsolidado(){
                 given()
                .contentType(ContentType.JSON)
                .headers("Knative-Serving-Tag", ConfVariables.getTicket())
                .headers("x-access-token",ConfVariables.getToken())
                .get("users/consolidated")
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.balance[0].symbol",equalTo("PTR"))
                .body("data.balance[0].name",equalTo("Petro"))
                .body("data.balance[0].amount",notNullValue())
                .body("data.balance[0].amountPtr",notNullValue())
                .body("data.balance[0].fiat",notNullValue())
                .body("data.balance[0].qpon",notNullValue());
    }


    @Then("Consulta de id de usuario por correo {string}")
    public void consultaUsuarioCorreo(String correo){

        BuscarIdCuentaRequest cuenta= new BuscarIdCuentaRequest();
        cuenta.setEmail(correo);

        BuscarIdCuentaResponse cuentaResponse = given()
                .body(cuenta)
                .contentType(ContentType.JSON)
                .headers("x-access-token",ConfVariables.getToken(),"Knative-Serving-Tag",ConfVariables.getTicket())
                .when()
                .post("users/get-userid")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(BuscarIdCuentaResponse.class);
              assertThat(cuentaResponse.getData(),equalTo("6288041cdf3082f3a5007214"));

              idCuenta=cuentaResponse.getData();
    }

    @Then("Consulta de servico de nombre, correo o direccion con {string}")
    public void consultaNombreDireccionCorreo(String moneda){

        BuscarDireccionRequest cuenta= new BuscarDireccionRequest();
        cuenta.setAccount(idCuenta);
        cuenta.setCoin(moneda);

        BuscarDireccionResponse cuentaResponse = given()
                .contentType(ContentType.JSON)
                .when()
                .headers("Knative-Serving-Tag", ConfVariables.getTicket())
                .headers("x-access-token",ConfVariables.getToken())
                .body(cuenta)
                .post("node-transactions/search-address")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(BuscarDireccionResponse.class);
    }




}
