package com.shpapth.githubrepolister.githubclient.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public record GithubRepoSearchQuery(LocalDate created, String language) implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
}
