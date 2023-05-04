package com.shpapth.githubrepolister.githubclient;

import com.shpapth.githubrepolister.githubclient.config.GithubFeignConfig;
import com.shpapth.githubrepolister.githubclient.model.GitHubRepoSearchQueryExpander;
import com.shpapth.githubrepolister.githubclient.model.GithubRepoSearchQuery;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@FeignClient(name = "${github.client.search.name}", url = "${github.client.search.url}",
		configuration = GithubFeignConfig.class)
public interface GithubSearchFeignClient {

	@RequestLine("GET /repositories?q={query}&page={page}&per_page={perPage}&sort={sort}&order={order}")
	@Headers("Content-Type: application/json")
	ResponseEntity<String> searchRepositories(
			@Param(expander = GitHubRepoSearchQueryExpander.class) GithubRepoSearchQuery query,
			@Param int page,
			@Param int perPage,
			@Param String sort,
			@Param String order);
}