package com.solvd.githubapi.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.R;
import com.solvd.githubapi.config.GitHubProperties;

@Endpoint(url = "${github_base_url}/users/${username}", methodType = HttpMethodType.GET)
@RequestTemplatePath(path = "api/users/get_user.ftl")
@ResponseTemplatePath(path = "api/users/rs/get_user_rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetUserMethod extends AbstractApiMethodV2 {

    public GetUserMethod(String username) {
        replaceUrlPlaceholder("github_base_url", GitHubProperties.baseUrl());
        replaceUrlPlaceholder("username", username);
        setHeader("Authorization", "Bearer " + GitHubProperties.token());
        setHeader("Accept", "application/vnd.github+json");
        addProperty("username", username);
        addProperty("username", R.CONFIG.get("github_username"));
    }
}
