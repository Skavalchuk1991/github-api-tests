package com.solvd.githubapi.test;

import com.solvd.githubapi.api.CreateRepoMethod;
import com.solvd.githubapi.api.DeleteRepoMethod;
import com.solvd.githubapi.config.GitHubProperties;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteRepoTest {

    private static final String REPO_TO_DELETE = "carina-delete-test-" + System.currentTimeMillis();

    @BeforeClass
    public void createRepoToDelete() {
        CreateRepoMethod create = new CreateRepoMethod(REPO_TO_DELETE);
        create.callAPI();
    }

    @Test(priority = 1)
    public void testDeleteExistingRepo() {
        DeleteRepoMethod method = new DeleteRepoMethod(
                GitHubProperties.username(),
                REPO_TO_DELETE
        );
        Response rs = method.callAPI();
        Assert.assertEquals(rs.statusCode(), 204, "Delete should return 204 No Content");
    }

    @Test(priority = 2)
    public void testDeleteNonExistingRepo() {
        DeleteRepoMethod method = new DeleteRepoMethod(
                GitHubProperties.username(),
                "repo-that-does-not-exist-xyz-99999"
        );
        Response rs = method.callAPI();
        Assert.assertEquals(rs.statusCode(), 404, "Delete non-existing repo should return 404");
    }
}
