package com.qa25.propets.Tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.qa25.propets.Model.User;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserSignInRestAPITests extends TestBase{
    String login = "api/auth/login";

    @Test(dataProvider = "validUserSignInFromFile",dataProviderClass = DataProviders.class, enabled = true)
    public void userSignInRestAssuredPositiveTest(User user){

        RequestSpecification httpRequest = io.restassured.RestAssured.given();
        Response response = httpRequest.given().header("Content-Type","application/json").given()
                .request().body("{\n" +
                        "            \"email\": \"" + user.getEmail() + "\",\n" +
                        "            \"password\": \"" + user.getPassword() + "\"\n" +
                        "        }")
        .when().post(appManager.getBaseURL() + login);
        // .when().post("https://propets-mern.herokuapp.com/api/auth/login");

        String responseBody = response.getBody().asString();
 //       logger.info("!!! --> Response body: " + responseBody);

        int statusCode = response.getStatusCode();
        JsonElement parsed = new JsonParser().parse(responseBody);
        String email = parsed.getAsJsonObject().get("email").toString();
        String userId = parsed.getAsJsonObject().get("id").toString();
        String token = parsed.getAsJsonObject().get("token").toString();

        logger.info("!!! --> Actual status Code = " + statusCode + ". Expected status Code = 200.");
        logger.info("    --> User Email = " + email + ". User ID = " + userId);
        logger.info("    --> Token = " + token);

        Assert.assertTrue(statusCode == 200);
    }

    @Test (dataProvider = "invalidUserSignInFromFile",dataProviderClass = DataProviders.class, enabled = true)
    public void userSignInRestAssuredNegativeTest(User user) throws InterruptedException {

        RequestSpecification httpRequest = io.restassured.RestAssured.given();
        Response response = httpRequest.given().header("Content-Type","application/json").given()
                .request().body("{\n" +
                        "            \"email\": \"" + user.getEmail() + "\",\n" +
                        "            \"password\": \"" + user.getPassword() + "\"\n" +
                        "        }")
                .when().post(appManager.getBaseURL() + login);

        String responseBody = response.getBody().asString();
//        logger.info("!!! --> Response body: " + responseBody);
        int statusCode = response.getStatusCode();
        logger.info("!!! --> Actual status Code = " + statusCode + ". Expected status Code = 400.");

        JsonElement parsed = new JsonParser().parse(responseBody);

        try{
            String message = parsed.getAsJsonObject().get("message").toString();
            logger.info("!!! --> Actual message = " + message);
        }catch (NullPointerException e){}
        try{
            String userId = parsed.getAsJsonObject().get("userId").toString();
            logger.info("    --> User ID = " + userId);
        }catch (NullPointerException e){}
        try{
            String param = parsed.getAsJsonObject().get("errors").getAsJsonArray().get(0).getAsJsonObject().get("param").toString();
            String msg = parsed.getAsJsonObject().get("errors").getAsJsonArray().get(0).getAsJsonObject().get("msg").toString();
            logger.info("    --> Wrong data in: " + param + ". Corresponding message: " + msg);
        }catch (NullPointerException e){}

        Assert.assertTrue(statusCode == 400);

    }

}
