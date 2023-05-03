package com.shpapth.githubrepolister.api.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shpapth.githubrepolister.api.exception.InvalidResponseException;
import com.shpapth.githubrepolister.api.model.GithubRepoDto;
import com.shpapth.githubrepolister.api.validator.ListRepositoryValidator;
import com.shpapth.githubrepolister.githubclient.GithubSearchFeignClient;
import com.shpapth.githubrepolister.githubclient.model.GithubRepoSearchQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListGithubRepoServiceImpl implements ListGithubRepoService {

	private static final String DEFAULT_SORT = "stars";

	private static final String DEFAULT_SORT_DIRECTION = "desc";

	private static final int DEFAULT_PAGE_NUMBER = 1;

	private final GithubSearchFeignClient githubSearchFeignClient;

	private final ObjectMapper apiObjectMapper;

	private final ListRepositoryValidator listRepositoryValidator;

	@Override
	public List<GithubRepoDto> getRepositories(LocalDate createdFrom, int limit, String language) {

		listRepositoryValidator.validate(createdFrom, limit);

		ResponseEntity<String> stringResponseEntity = githubSearchFeignClient.searchRepositories(
				new GithubRepoSearchQuery(createdFrom, language),
				DEFAULT_PAGE_NUMBER,
				limit,
				DEFAULT_SORT,
				DEFAULT_SORT_DIRECTION
		);
		return parseValue(stringResponseEntity.getBody());
	}

	private List<GithubRepoDto> parseValue(String body) {
		try {
			JsonNode items = apiObjectMapper.readTree(body).get("items");
			return apiObjectMapper.readValue(items.toString(), new TypeReference<>() {});
		} catch (Exception e) {
			throw new InvalidResponseException();
		}
	}
}
