package Authorization;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Authorization {

    public String Auth()
    {
        Response resp= RestAssured.
        given()
                .formParam("client_id","nucleo")
                .formParam("username","jsuarez")
                .formParam("password","200691Zero")
                .formParam("scope","openid")
                .formParam("grant_type","password")
                .post("https://secure-sso.movealong.tech/auth/realms/Nucleo/protocol/openid-connect/token");

       // System.out.println("Code"+resp.getStatusCode());
       // System.out.println("Code"+resp.getBody().asString());
        String access_token=resp.jsonPath().get("access_token");
        return access_token;
    }


}
