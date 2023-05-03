package com.shpapth.githubrepolister.githubclient.model;

import com.shpapth.githubrepolister.githubclient.model.GithubRepoSearchQuery;
import feign.Param;
import org.springframework.util.StringUtils;

import java.time.format.DateTimeFormatter;

public class GitHubRepoSearchQueryExpander implements Param.Expander {

	@Override
	public String expand(Object value) {
		assert value instanceof GithubRepoSearchQuery;
		GithubRepoSearchQuery githubRepoSearchQuery = (GithubRepoSearchQuery) value;

		String requiredParam = "created:>=%s"
				.formatted(githubRepoSearchQuery.created().format(DateTimeFormatter.ISO_DATE));

		if (StringUtils.hasText(githubRepoSearchQuery.language())) {
			requiredParam = requiredParam.concat("+")
			                             .concat("language:%s".formatted(githubRepoSearchQuery.language()));
		}

		return requiredParam;
	}
}
