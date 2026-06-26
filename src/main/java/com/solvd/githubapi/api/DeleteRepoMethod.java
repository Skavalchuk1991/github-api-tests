package com.solvd.githubapi.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.solvd.githubapi.config.GitHubProperties;

@Endpoint(url = "${github_base_url}/repos/${owner}/${repo}", methodType = HttpMethodType.DELETE)
@RequestTemplatePath(path = "api/repos/delete_repo.ftl")
@SuccessfulHttpStatus(status = HttpResponseStatusType.NO_CONTENT_204)
public class DeleteRepoMethod extends AbstractApiMethodV2 {

    public DeleteRepoMethod(String owner, String repo) {
        replaceUrlPlaceholder("github_base_url", GitHubProperties.baseUrl());
        replaceUrlPlaceholder("owner", owner);
        replaceUrlPlaceholder("repo", repo);
        setHeader("Authorization", "Bearer " + GitHubProperties.token());
        setHeader("Accept", "application/vnd.github+json");
    }
}
