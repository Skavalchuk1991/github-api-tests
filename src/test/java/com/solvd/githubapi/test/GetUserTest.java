package com.solvd.githubapi.test;

import com.solvd.githubapi.api.GetUserMethod;
import com.solvd.githubapi.config.GitHubProperties;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserTest {

    @Test
    public void testGetExistingUser() {
        GetUserMethod method = new GetUserMethod(GitHubProperties.username());
        Assert.assertEquals(method.callAPI().statusCode(), 200, "Get user should return 200");
    }

    @Test
    public void testGetUserLoginField() {
        GetUserMethod method = new GetUserMethod(GitHubProperties.username());
        Response rs = method.callAPI();
        String login = rs.jsonPath().getString("login");
        Assert.assertEquals(login, GitHubProperties.username(), "Login should match username");
    }

    @Test
    public void testGetUserTypeField() {
        GetUserMethod method = new GetUserMethod(GitHubProperties.username());
        Response rs = method.callAPI();
        String type = rs.jsonPath().getString("type");
        Assert.assertEquals(type, "User", "Type should be User");
    }

    @Test
    public void testGetNonExistingUser() {
        GetUserMethod method = new GetUserMethod("this-user-does-not-exist-xyz-12345");
        Response rs = method.callAPI();
        Assert.assertEquals(rs.statusCode(), 404, "Non-existing user should return 404");
    }
}
