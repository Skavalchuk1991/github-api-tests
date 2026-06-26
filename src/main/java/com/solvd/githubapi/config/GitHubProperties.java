package com.solvd.githubapi.config;

import com.zebrunner.carina.utils.R;

public class GitHubProperties {

    public static String get(String key) {
        return R.CONFIG.get(key);
    }

    public static String token() {
        return R.CONFIG.get("github_token");
    }

    public static String baseUrl() {
        return R.CONFIG.get("github_base_url");
    }

    public static String username() {
        return R.CONFIG.get("github_username");
    }

    public static String repo() {
        return R.CONFIG.get("github_repo");
    }
}
