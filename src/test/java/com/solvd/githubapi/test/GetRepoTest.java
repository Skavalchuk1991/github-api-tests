package com.solvd.githubapi.test;

import com.solvd.githubapi.api.GetRepoMethod;
import com.solvd.githubapi.config.GitHubProperties;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetRepoTest {

    @Test
    public void testGetExistingRepo() {
        GetRepoMethod method = new GetRepoMethod(
                GitHubProperties.username(),
                GitHubProperties.repo()
        );
        Assert.assertEquals(method.callAPI().statusCode(), 200, "Get repo should return 200");
    }

    @Test
    public void testGetRepoNameField() {
        GetRepoMethod method = new GetRepoMethod(
                GitHubProperties.username(),
                GitHubProperties.repo()
        );
        Response rs = method.callAPI();
        String name = rs.jsonPath().getString("name");
        Assert.assertEquals(name, GitHubProperties.repo(), "Repo name should match");
    }

    @Test
    public void testGetRepoOwnerField() {
        GetRepoMethod method = new GetRepoMethod(
                GitHubProperties.username(),
                GitHubProperties.repo()
        );
        Response rs = method.callAPI();
        String owner = rs.jsonPath().getString("owner.login");
        Assert.assertEquals(owner, GitHubProperties.username(), "Owner login should match username");
    }

    @Test
    public void testGetNonExistingRepo() {
        GetRepoMethod method = new GetRepoMethod(
                GitHubProperties.username(),
                "this-repo-does-not-exist-xyz-99999"
        );
        Response rs = method.callAPI();
        Assert.assertEquals(rs.statusCode(), 404, "Non-existing repo should return 404");
    }
}
