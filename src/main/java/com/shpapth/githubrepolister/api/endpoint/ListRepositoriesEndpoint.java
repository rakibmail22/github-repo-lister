package com.shpapth.githubrepolister.api.endpoint;

import com.shpapth.githubrepolister.api.model.GithubRepoDto;
import com.shpapth.githubrepolister.api.service.ListGithubRepoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@ApiV1Controller
@RequiredArgsConstructor
public class ListRepositoriesEndpoint {

	private final ListGithubRepoService listGithubRepoService;

	@GetMapping(value = "/repositories")
	public ResponseEntity<List<GithubRepoDto>> repositories(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	                                                        @RequestParam LocalDate createdFrom,
	                                                        @RequestParam int limit,
	                                                        @RequestParam(required = false) String language) {

		return ResponseEntity.ok(listGithubRepoService.getRepositories(createdFrom, limit, language));
	}
}
