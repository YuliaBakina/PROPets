package com.qa25.propets.Tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.qa25.propets.Model.User;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserSignupRestAPITests extends TestBase{
    String signup = "api/auth/register";

    @Test(dataProvider = "validUserSignUpFromFile",dataProviderClass = DataProviders.class, enabled = true)
    public void userSignUpRestAssuredPositiveTest(User user)  {

        RequestSpecification httpRequest = io.restassured.RestAssured.given();
        Response response = httpRequest.given().header("Content-Type","application/json").given()
                .request().body("{\n" +
                        "            \"email\": \"" + user.getEmail() + "\",\n" +
                        "                \"name\": \"" + user.getName() + "\",\n" +
                        "                \"password\": \"" + user.getPassword() + "\"\n" +
                        "        }")
                .when().post(appManager.getBaseURL() + signup);

        String responseBody = response.getBody().asString();
 //       logger.info("!!! --> Response body: " + responseBody);

        int statusCode = response.getStatusCode();
        JsonElement parsed = new JsonParser().parse(responseBody);
        String message = parsed.getAsJsonObject().get("message").toString();
        String userId = parsed.getAsJsonObject().get("userId").toString();

        logger.info("!!! --> Actual status Code = " + statusCode + ". Expected status Code = 201.");
        logger.info("!!! --> Actual message = " + message + ". User ID = " + userId);

        Assert.assertTrue(statusCode == 201);

    }

    @Test (dataProvider = "invalidUserSignUpFromFile",dataProviderClass = DataProviders.class, enabled = true)
    public void userSignUpRestAssuredNegativeTest(User user) throws InterruptedException {

        RequestSpecification httpRequest = io.restassured.RestAssured.given();
        Response response = httpRequest.given().header("Content-Type","application/json").given()
                .request().body("{\n" +
                        "            \"email\": \"" + user.getEmail() + "\",\n" +
                        "                \"name\": \"" + user.getName() + "\",\n" +
                        "                \"password\": \"" + user.getPassword() + "\"\n" +
                        "        }")
                .when().post(appManager.getBaseURL() + signup);

        String responseBody = response.getBody().asString();
    //    logger.info("!!! --> Response body: " + responseBody);
        int statusCode = response.getStatusCode();
        JsonElement parsed = new JsonParser().parse(responseBody);
        String message = parsed.getAsJsonObject().get("message").toString();

        logger.info("!!! --> Actual status Code = " + statusCode + ". Expected status Code = 400.");

        if(statusCode != 400) {
            String userId = parsed.getAsJsonObject().get("userId").toString();

            logger.info("!!! --> Actual message = " + message + ". User ID = " + userId);
        }else{
            String param = parsed.getAsJsonObject().get("errors").getAsJsonArray().get(0).getAsJsonObject().get("param").toString();
            String msg = parsed.getAsJsonObject().get("errors").getAsJsonArray().get(0).getAsJsonObject().get("msg").toString();

            logger.info("!!! --> Actual message = " + message);
            logger.info("    --> Wrong data in: " + param + ". Corresponding message: " + msg);
        }

        Assert.assertTrue(statusCode == 400);

    }

}
