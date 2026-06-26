package com.solvd.githubapi.test;

import com.solvd.githubapi.api.GetIssuesMethod;
import com.solvd.githubapi.config.GitHubProperties;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetIssuesTest {

    @Test
    public void testGetIssuesReturns200() {
        GetIssuesMethod method = new GetIssuesMethod(
                GitHubProperties.username(),
                GitHubProperties.repo()
        );
        Response rs = method.callAPI();
        Assert.assertEquals(rs.statusCode(), 200, "Issues endpoint should return 200");
    }

    @Test
    public void testGetIssuesReturnsList() {
        GetIssuesMethod method = new GetIssuesMethod(
                GitHubProperties.username(),
                GitHubProperties.repo()
        );
        Response rs = method.callAPI();
        Assert.assertNotNull(rs.jsonPath().get(), "Response body should not be null");
    }

    @Test
    public void testGetIssuesForNonExistingRepo() {
        GetIssuesMethod method = new GetIssuesMethod(
                GitHubProperties.username(),
                "non-existing-repo-xyz-99999"
        );
        Response rs = method.callAPI();
        Assert.assertEquals(rs.statusCode(), 404, "Non-existing repo issues should return 404");
    }
}
