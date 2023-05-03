package com.shpapth.githubrepolister.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shpapth.githubrepolister.api.model.GithubRepoDto;
import com.shpapth.githubrepolister.api.validator.ListRepositoryValidator;
import com.shpapth.githubrepolister.githubclient.GithubSearchFeignClient;
import com.shpapth.githubrepolister.githubclient.model.GithubRepoSearchQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ListGithubRepoServiceTest {

	@Mock
	private ListRepositoryValidator listRepositoryValidator;

	@Mock
	private GithubSearchFeignClient githubSearchFeignClient;

	@Autowired
	private ObjectMapper apiObjectMapper;

	private ListGithubRepoServiceImpl listGithubRepoService;

	@BeforeEach
	public void setup() throws Exception {
		try (var obj = MockitoAnnotations.openMocks(this)) {
			this.listGithubRepoService = new ListGithubRepoServiceImpl(
					githubSearchFeignClient,
					apiObjectMapper,
					listRepositoryValidator
			);
		}
	}

	@Test
	public void getRepositories_GivenAValidParams_ReturnsCorrectNumberOfDto() throws IOException {
		String mockResponseJson = new ClassPathResource("mock-response.json")
				.getContentAsString(StandardCharsets.UTF_8);

		Mockito.doNothing().when(listRepositoryValidator).validate(any(LocalDate.class), anyInt());
		Mockito.doReturn(ResponseEntity.ok(mockResponseJson)).when(githubSearchFeignClient)
		       .searchRepositories(any(GithubRepoSearchQuery.class),
		                           anyInt(),
		                           anyInt(),
		                           anyString(),
		                           anyString());

		List<GithubRepoDto> response =
				listGithubRepoService.getRepositories(LocalDate.now(), 10, "Random String");

		Assertions.assertEquals(10, response.size());
	}

	@Test
	public void getRepositories_GivenValidParams_ReturnCorrectDtoResponse() throws IOException {
		String mockResponseJson = new ClassPathResource("mock-response.json")
				.getContentAsString(StandardCharsets.UTF_8);

		Mockito.doNothing().when(listRepositoryValidator).validate(any(LocalDate.class), anyInt());
		Mockito.doReturn(ResponseEntity.ok(mockResponseJson)).when(githubSearchFeignClient)
		       .searchRepositories(any(GithubRepoSearchQuery.class),
		                           anyInt(),
		                           anyInt(),
		                           anyString(),
		                           anyString());

		GithubRepoDto firstResponse =
				listGithubRepoService.getRepositories(LocalDate.now(), 10, "Random String")
				                     .stream()
				                     .findFirst()
				                     .orElseThrow();

		Assertions.assertEquals("463260491", firstResponse.id());
		Assertions.assertEquals("intro_continual_learning", firstResponse.name());
		Assertions.assertEquals(343, firstResponse.starCount());
		Assertions.assertEquals("https://github.com/clam004", firstResponse.url());
		Assertions.assertEquals("Jupyter Notebook", firstResponse.language());

	}

	private List<GithubRepoDto> getMockResponseDto() {
		return List.of(
				new GithubRepoDto("demo_id_1", "demo_name_1", 112,
				                  ZonedDateTime.now(), "https://demo.url.1", "java"),
				new GithubRepoDto("demo_id_2", "demo_name_2", 113,
				                  ZonedDateTime.now(), "https://demo.url.2", "javascript")

		);
	}
}
