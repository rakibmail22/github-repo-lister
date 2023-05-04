package com.shpapth.githubrepolister.githubclient.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class GithubSearchQueryParamDecoderInterceptor implements RequestInterceptor {

	private static final Map<String, String> encodedDecodedLiteralMap = Map.of(
			"%2B", "+"
	);

	@Override
	public void apply(RequestTemplate template) {
		Collection<String> requestVariables = template.getRequestVariables();

		String queryString = template.queries().get("q").stream().findFirst().orElseThrow();

		for (var entry : encodedDecodedLiteralMap.entrySet()) {
			queryString = queryString.replaceAll(entry.getKey(), entry.getValue());
		}

		Map<String, Collection<String>> queries = new LinkedHashMap<>(template.queries());
		queries.put("q", List.of(queryString));

		template.queries(null);
		template.queries(queries);

		log.debug("req {}", requestVariables);
	}
}