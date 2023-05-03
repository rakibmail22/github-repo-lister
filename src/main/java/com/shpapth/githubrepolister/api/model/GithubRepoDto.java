package com.shpapth.githubrepolister.api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubRepoDto(String id, String name,
                            @JsonAlias("stargazers_count")
                            @JsonProperty("star_count") int starCount,

                            @JsonProperty("created_at") ZonedDateTime createdAt,

                            @JsonAlias("html_url")
                            @JsonProperty("repo_url") String url,
                            String language) {
}
