package com.solvd.githubapi.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.solvd.githubapi.config.GitHubProperties;

@Endpoint(url = "${github_base_url}/user/repos", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/repos/create_repo.ftl")
@ResponseTemplatePath(path = "api/repos/rs/create_repo_rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.CREATED_201)
public class CreateRepoMethod extends AbstractApiMethodV2 {

    public CreateRepoMethod(String repoName) {
        replaceUrlPlaceholder("github_base_url", GitHubProperties.baseUrl());
        setHeader("Authorization", "Bearer " + GitHubProperties.token());
        setHeader("Accept", "application/vnd.github+json");
        addProperty("repo_name", repoName);
        addProperty("private", "false");
    }
}
