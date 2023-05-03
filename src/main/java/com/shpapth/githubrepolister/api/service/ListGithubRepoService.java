package com.shpapth.githubrepolister.api.service;

import com.shpapth.githubrepolister.api.model.GithubRepoDto;

import java.time.LocalDate;
import java.util.List;

public interface ListGithubRepoService {

	List<GithubRepoDto> getRepositories(LocalDate createdFrom, int limit, String language);
}
