package com.solvd.githubapi.config;

public class GitHubProperties {

    private static final java.util.Properties props = new java.util.Properties();

    static {
        try {
            props.load(GitHubProperties.class.getClassLoader()
                    .getResourceAsStream("api.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Cannot load api.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static String token() {
        return get("github_token");
    }

    public static String baseUrl() {
        return get("github_base_url");
    }

    public static String username() {
        return get("github_username");
    }

    public static String repo() {
        return get("github_repo");
    }
}
