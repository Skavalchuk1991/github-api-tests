package com.solvd.githubapi.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.solvd.githubapi.config.GitHubProperties;

@Endpoint(url = "${github_base_url}/repos/${owner}/${repo}/issues", methodType = HttpMethodType.GET)
@RequestTemplatePath(path = "api/issues/get_issues.ftl")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetIssuesMethod extends AbstractApiMethodV2 {

    public GetIssuesMethod(String owner, String repo) {
        replaceUrlPlaceholder("github_base_url", GitHubProperties.baseUrl());
        replaceUrlPlaceholder("owner", owner);
        replaceUrlPlaceholder("repo", repo);
        setHeader("Authorization", "Bearer " + GitHubProperties.token());
        setHeader("Accept", "application/vnd.github+json");
    }
}
