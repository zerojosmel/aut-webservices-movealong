package StepDefinition;

import Authorization.Authorization;
import Configuracion.ConfVariables;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class GetConsolidated {

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

}
