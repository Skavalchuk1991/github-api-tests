package com.solvd.githubapi.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.solvd.githubapi.config.GitHubProperties;

@Endpoint(url = "${github_base_url}/repos/${owner}/${repo}", methodType = HttpMethodType.GET)
@RequestTemplatePath(path = "api/repos/get_repo.ftl")
@ResponseTemplatePath(path = "api/repos/rs/get_repo_rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetRepoMethod extends AbstractApiMethodV2 {

    public GetRepoMethod(String owner, String repo) {
        replaceUrlPlaceholder("github_base_url", GitHubProperties.baseUrl());
        replaceUrlPlaceholder("owner", owner);
        replaceUrlPlaceholder("repo", repo);
        setHeader("Authorization", "Bearer " + GitHubProperties.token());
        setHeader("Accept", "application/vnd.github+json");
        addProperty("owner", owner);
        addProperty("repo", repo);
    }
}
