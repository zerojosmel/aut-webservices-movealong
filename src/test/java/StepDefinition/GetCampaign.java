package StepDefinition;

import Authorization.Authorization;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetCampaign{

@Given("solicito accesos al portal de campana")
public  void setup(){
    RestAssured.baseURI="https://campaign-api.movealong.tech";
    RestAssured.basePath="/campaigns";
    RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    RestAssured.requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .build()
            .auth()
            .oauth2(new Authorization().Auth());
}
@Then("Consulta de servico de campana")
    public void consultaDeCamapana(){
        given()
                .contentType(ContentType.JSON)
                .headers("Knative-Serving-Tag","nucleo-1")
                .get("getById/51")
                .then()
                .statusCode(200)
                .body("id",equalTo(51) );
    }
}
