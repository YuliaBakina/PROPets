package com.qa25.propets.Tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class HomePagePostsRestAPITests extends TestBase{
    String posts = "api/post/home";

    @Test(enabled = true)
    public void getAllPostsHomePageRestAssuredPositiveTest(){
        RequestSpecification httpRequest = io.restassured.RestAssured.given();
        Response response = httpRequest.given().request().when().get(appManager.getBaseURL() + posts);
        //Response response = httpRequest.given().request().when().get("https://propets-mern.herokuapp.com/api/post/home");

        String responseBody = response.getBody().asString();
//        logger.info(responseBody);

        JsonElement parsed = new JsonParser().parse(responseBody);

        int arrSize = parsed.getAsJsonArray().size();
        logger.info("!!! --> There are " + arrSize + " posts available on Home page.");

        for(int i = 0; i < arrSize; i++){

            String favorite = parsed.getAsJsonArray().get(i).getAsJsonObject().get("isFavorite").toString();
            String time = parsed.getAsJsonArray().get(i).getAsJsonObject().get("time").toString();
            String postText = parsed.getAsJsonArray().get(i).getAsJsonObject().get("postText").toString();
            String userName = parsed.getAsJsonArray().get(i).getAsJsonObject().get("userName").toString();
            String userId = parsed.getAsJsonArray().get(i).getAsJsonObject().get("userId").toString();

            logger.info("   --> " + (i+1) + ") Post time: " + time + ". Is post favorite: " + favorite);
            logger.info("   -->    Post author: " + userName + " (userId = " + userId + ")");
            logger.info("   -->    Post text: " + postText);

        }


    }
}
