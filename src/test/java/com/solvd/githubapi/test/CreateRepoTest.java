package com.solvd.githubapi.test;

import com.solvd.githubapi.api.CreateRepoMethod;
import com.solvd.githubapi.api.DeleteRepoMethod;
import com.solvd.githubapi.config.GitHubProperties;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class CreateRepoTest {

    private static final String TEST_REPO = "carina-test-repo-" + System.currentTimeMillis();

    @Test(priority = 1)
    public void testCreateRepoReturns201() {
        CreateRepoMethod method = new CreateRepoMethod(TEST_REPO);
        Response rs = method.callAPI();
        Assert.assertEquals(rs.statusCode(), 201, "Create repo should return 201");
    }

    @Test(priority = 2, dependsOnMethods = "testCreateRepoReturns201")
    public void testCreatedRepoNameMatches() {
        CreateRepoMethod method = new CreateRepoMethod(TEST_REPO + "-2");
        Response rs = method.callAPI();
        String name = rs.jsonPath().getString("name");
        Assert.assertEquals(name, TEST_REPO + "-2", "Created repo name should match");

        // cleanup second repo
        DeleteRepoMethod delete = new DeleteRepoMethod(GitHubProperties.username(), TEST_REPO + "-2");
        delete.callAPI();
    }

    @AfterClass
    public void cleanup() {
        DeleteRepoMethod delete = new DeleteRepoMethod(GitHubProperties.username(), TEST_REPO);
        delete.callAPI();
    }
}
